package com.software.rateit.services.Track;

import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.PaginationContext;
import com.software.rateit.DTO.Track.TrackDTO;
import com.software.rateit.DTO.Track.TrackMapper;
import com.software.rateit.DTO.Track.TrackWrapper;
import com.software.rateit.Entity.Track;
import com.software.rateit.exceptions.NotFoundException;
import com.software.rateit.repositories.TrackRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TrackServiceImpl implements TrackService {

    private static final Logger LOG = LoggerFactory.getLogger(TrackServiceImpl.class);

    private TrackMapper mapper = Mappers.getMapper(TrackMapper.class);

    @Value("${server-address}")
    private String serverAddress;

    @Autowired
    private TrackRepository trackRepository;

    @Override
    public ResponseEntity<TrackDTO> getOneTrack(long id) {

        Track track = trackRepository.findOneById(id);
        if(track == null) {
            throw  new NotFoundException("Track not found");
        }
        return new ResponseEntity<>(mapper.mapToTrackDTO(track), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrackWrapper> getAllTracks(Predicate predicate, Pageable pageable) {
        Page<Track> trackEntities = trackRepository.findAll(predicate, pageable);
        Iterable<TrackDTO> tracks = mapper.mapToTrackDTOIterable(trackEntities);

        PaginationContext info;

        if(trackEntities.getTotalPages() <= pageable.getPageNumber()+1) {
            info = new PaginationContext(pageable.getPageNumber(), trackEntities.getTotalPages(), pageable.getPageSize(),
                    trackEntities.getTotalElements(), null);
        } else {
            info = new PaginationContext(pageable.getPageNumber(), trackEntities.getTotalPages(), pageable.getPageSize(),
                    trackEntities.getTotalElements(), serverAddress + "/tracks?" + query(predicate) + "page="
                    + (pageable.getPageNumber()+1) + "&size=" + pageable.getPageSize());
        }
        return new ResponseEntity<>(new TrackWrapper(tracks, info), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TrackDTO> addTrack(TrackDTO trackDTO) {

        Track track = mapper.mapToTrack(trackDTO);
        Track response = trackRepository.save(track);

        return new ResponseEntity<>(mapper.mapToTrackDTO(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTrack(long id) {

        Track track = trackRepository.findOneById(id);
        if(track == null) {
            throw new NotFoundException("Track not found");
        }
        trackRepository.delete(track);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<TrackDTO> editTrack(long id, TrackDTO trackDTO) {

        Track track = trackRepository.findOneById(id);

        if(track == null) {
            throw new NotFoundException("Track not found");
        }

        if(trackDTO.getGenre() != null){
            track.setGenre(trackDTO.getGenre());
        }
        if(trackDTO.getReleaseDate() != null){
            track.setReleaseDate(trackDTO.getReleaseDate());
        }
        if(trackDTO.getTitle() != null){
            track.setTitle(trackDTO.getTitle());
        }
        Track response = trackRepository.save(track);
        return new ResponseEntity<>(mapper.mapToTrackDTO(response), HttpStatus.OK);
    }


    private String query(Predicate predicate) {
        StringBuilder builder = new StringBuilder();
        Pattern p = Pattern.compile("[a-zA-Z]+,[a-zA-Z]+");
        try{
            Matcher m = p.matcher(predicate.toString());
            while (m.find()) {
                String[] parts = m.group().split(",");
                builder.append(parts[0]).append("=").append(parts[1]).append("&");
            }
        } catch (Exception e) {
            LOG.info("No query");
        }

        return builder.toString();
    }
}
