package com.software.rateit.services.Artist;

import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.Artist.ArtistDTO;
import com.software.rateit.DTO.Artist.ArtistMapper;
import com.software.rateit.DTO.Artist.ArtistWrapper;
import com.software.rateit.DTO.PaginationContext;
import com.software.rateit.Entity.Artist;
import com.software.rateit.Entity.CD;
import com.software.rateit.exceptions.NotFoundException;
import com.software.rateit.repositories.ArtistRepository;
import com.software.rateit.repositories.CDRepository;
import org.aspectj.weaver.ast.Not;
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
public class ArtistServiceImpl implements ArtistService {

    @Value("${server-address}")
    private String serverAddress;

    private static final Logger LOG = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private ArtistMapper mapper = Mappers.getMapper(ArtistMapper.class);

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private CDRepository cdRepository;

    @Override
    public ResponseEntity<ArtistWrapper> getAllArtists(Predicate predicate, Pageable pageable) {

        Page<Artist> artistEntities = artistRepository.findAll(predicate, pageable);
        Iterable<ArtistDTO> artists = mapper.mapToArtistDTOIterable(artistEntities);

        PaginationContext info;

        if(artistEntities.getTotalPages() <= pageable.getPageNumber()+1) {
            info = new PaginationContext(pageable.getPageNumber(), artistEntities.getTotalPages(), pageable.getPageSize(),
                    artistEntities.getTotalElements(), null);
        } else {
            info = new PaginationContext(pageable.getPageNumber(), artistEntities.getTotalPages(), pageable.getPageSize(),
                    artistEntities.getTotalElements(), serverAddress + "/artists?" + query(predicate) + "page="
                    + (pageable.getPageNumber()+1) + "&size=" + pageable.getPageSize());
        }

        return new ResponseEntity<>(new ArtistWrapper(artists, info), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArtistDTO> addNewArtist(ArtistDTO artist) {
        Artist aristEntity = mapper.mapToArtistEntity(artist);

        Artist response = artistRepository.save(aristEntity);

        return new ResponseEntity<>(mapper.mapToArtistDTO(response), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ArtistDTO> updateArtist(ArtistDTO artist, long id) {

        Artist artistEntity = artistRepository.findOneById(id);

        if(artistEntity == null)
            throw new NotFoundException("Artist with id " + id + "doesn't exist");

        if(artist.getStageName() != null)
            artistEntity.setStageName(artist.getStageName());

        Artist response = artistRepository.save(artistEntity);

        return new ResponseEntity<>(mapper.mapToArtistDTO(response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArtistDTO> getOneArtist(long id) {

        Artist artist = artistRepository.findOneById(id);

        return new ResponseEntity<>(mapper.mapToArtistDTOWithCollections(artist), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteArtist(long id) {
        if(artistRepository.findOneById(id) == null)
            throw new NotFoundException("Artist with id " + id + "doesn't exist");
        artistRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ArtistDTO> addCDToArtist(long id, long cdId) {
        Artist artist = artistRepository.findOneById(id);
        CD cd = cdRepository.findOneById(cdId);
        if(artist == null){
            throw new NotFoundException("Artist not found");
        }
        if(cd == null){
            throw new NotFoundException("CD not found");
        }

        artist.addCD(cd);

        artistRepository.save(artist);

        return new ResponseEntity<>(mapper.mapToArtistDTOWithCollections(artist), HttpStatus.OK);

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
