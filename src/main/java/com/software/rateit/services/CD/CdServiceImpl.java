package com.software.rateit.services.CD;

import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.CD.CDMapper;
import com.software.rateit.DTO.CD.CDWithCommentsDTO;
import com.software.rateit.DTO.CD.CDWrapper;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.Comments.CommentsDTO;
import com.software.rateit.DTO.Comments.CommentsMapper;
import com.software.rateit.DTO.PaginationContext;
import com.software.rateit.DTO.Track.NewTrackDTO;
import com.software.rateit.DTO.Track.TrackDTO;
import com.software.rateit.DTO.Track.TrackMapper;
import com.software.rateit.DTO.User.UserMapper;
import com.software.rateit.Entity.CD;
import com.software.rateit.Entity.Rate;
import com.software.rateit.Entity.Track;
import com.software.rateit.Entity.User;
import com.software.rateit.exceptions.NotFoundException;
import com.software.rateit.repositories.CDRepository;
import com.software.rateit.repositories.RateRepository;
import com.software.rateit.repositories.TrackRepository;
import com.software.rateit.repositories.UserRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CdServiceImpl implements CdService {

    @Value("${server-address}")
    private String serverAddress;

    private CDMapper mapper = Mappers.getMapper(CDMapper.class);
    private TrackMapper trackMapper = Mappers.getMapper(TrackMapper.class);
    private CommentsMapper commentsMapper = Mappers.getMapper(CommentsMapper.class);
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    private CDRepository cdRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private RateRepository rateRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CdServiceImpl.class);

    @Override
    public ResponseEntity<CDWrapper> findAllAlbums(Pageable pageable, Predicate predicate) {
        Page<CD> cdEntities = cdRepository.findAll(predicate, pageable);
        Iterable<CdDTO> cds = mapper.mapToCdDTOIterable(cdEntities);

        PaginationContext info;

        if (cdEntities.getTotalPages() <= pageable.getPageNumber() + 1) {
            info = new PaginationContext(pageable.getPageNumber(), cdEntities.getTotalPages(), pageable.getPageSize(),
                    cdEntities.getTotalElements(), null);
        } else {
            info = new PaginationContext(pageable.getPageNumber(), cdEntities.getTotalPages(), pageable.getPageSize(),
                    cdEntities.getTotalElements(), serverAddress + "/cds?" + query(predicate) + "page="
                    + (pageable.getPageNumber() + 1) + "&size=" + pageable.getPageSize());
        }

        return new ResponseEntity<>(new CDWrapper(cds, info), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CDWithCommentsDTO> findOneAlbum(long id) {
        CD cd = cdRepository.findOneById(id);
        List<CommentsDTO> list = new ArrayList<>();
        cd.getComments().forEach(comments -> {
            CommentsDTO comment = new CommentsDTO();
            comment.setUser(userMapper.mapToUserDTO(comments.getUser()));
            comment.setContent(comments.getContent());
            comment.setId(comments.getId());
            list.add(comment);
        });
        List<TrackDTO> trackDTOS = new ArrayList<>();
        cd.getCdtracks().forEach(track1 -> {
            TrackDTO dto = new TrackDTO();
            dto.setGenre(track1.getGenre());
            dto.setId(track1.getId());
            dto.setReleaseDate(track1.getReleaseDate());
            dto.setArtist(track1.getArtist());
            dto.setTitle(track1.getTitle());
            trackDTOS.add(dto);
        });

        CDWithCommentsDTO response = new CDWithCommentsDTO();
        response.setCd(mapper.mapToCdDTO(cd));
        response.setComments(list);
        response.getCd().setCdtracks(trackDTOS);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CdDTO> updateAlbum(long id, CdDTO cdDTO) {
        CD cd = cdRepository.findOneById(id);
        if(cd == null) {
            throw  new NotFoundException("CD not found");
        }
        if(cdDTO.getArtist() != null) {
            cd.setArtist(cdDTO.getArtist());
        }
        if(cdDTO.getGenre() != null) {
            cd.setGenre(cdDTO.getGenre());
        }
        if(cdDTO.getName() != null) {
            cd.setName(cdDTO.getName());
        }
        if(cdDTO.getPhotoURL() != null) {
            cd.setPhotoURL(cdDTO.getPhotoURL());
        }
        CD response = cdRepository.save(cd);
        return new ResponseEntity<>(mapper.mapToCdDTO(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAlbum(long id) {
        CD cd = cdRepository.findOneById(id);
        if(cd == null) {
            throw new NotFoundException("CD not found");
        }
        cdRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<CdDTO> addNewAlbum(CdDTO cd) {

        CD response = cdRepository.save(mapper.mapToCd(cd));

        return new ResponseEntity<>(mapper.mapToCdDTO(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Iterable<CdDTO>> getRatingList() {
        return new ResponseEntity<>(mapper.mapToCdDTOIterable(cdRepository.findTop25ByRatingLessThanOrderByRatingDesc(6)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CdDTO> rateCD(long id, long userId, float note) {
        CD cd = cdRepository.findOneById(id);
        Rate rate = rateRepository.findOneByCdIdAndUserId(id, userId);
        if(cd == null) {
            throw new NotFoundException("CD not found");
        }
        if(rate == null) {
            rateRepository.save(new Rate(note, id, userId));
            cd.setRatingCount(cd.getRatingCount() + 1);
            cd.setSumOfRating(cd.getSumOfRating() + note);
        } else {
            cd.setSumOfRating(cd.getSumOfRating() - rate.getRating() + note);
            rate.setRating(note);
            rateRepository.save(rate);

        }

        cd.setRating(cd.getSumOfRating()/cd.getRatingCount());

        CD response = cdRepository.save(cd);
        return new ResponseEntity<>(mapper.mapToCdDTO(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CdDTO> addTrackToAlbum(long cdId, NewTrackDTO newTrackDTO) {
        CD cd = cdRepository.findOneById(cdId);
        Track track = new Track();
        if(cd == null) {
            throw new NotFoundException("Album not found");
        }
        track.setTitle(newTrackDTO.getTitle());
        track.setGenre(cd.getGenre());
        track.setReleaseDate(cd.getReleased());
        track.setArtist(cd.getArtist());
        cd.addTrack(track);
        trackRepository.save(track);
        CD response = cdRepository.save(cd);
        List<TrackDTO> trackDTOS = new ArrayList<>();
        response.getCdtracks().forEach(track1 -> {
            TrackDTO dto = new TrackDTO();
            dto.setGenre(track1.getGenre());
            dto.setId(track1.getId());
            dto.setReleaseDate(track1.getReleaseDate());
            dto.setTitle(track1.getTitle());
            trackDTOS.add(dto);
        });

        CdDTO responseDTO = mapper.mapToCdDTO(response);
        responseDTO.setCdtracks(trackDTOS);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Iterable<TrackDTO>> getCdTracks(long id) {
        CD cd = cdRepository.findOneById(id);
        if(cd == null) {
            throw new NotFoundException("Album not found");
        }
        return new ResponseEntity<>(trackMapper.mapToTrackDTOIterable(cd.getCdtracks()), HttpStatus.OK);
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
