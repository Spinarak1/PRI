package com.software.rateit.services.CD;

import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.CD.CDMapper;
import com.software.rateit.DTO.CD.CDWrapper;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.PaginationContext;
import com.software.rateit.Entity.CD;
import com.software.rateit.exceptions.NotFoundException;
import com.software.rateit.repositories.CDRepository;
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
public class CdServiceImpl implements CdService {

    @Value("${server-address}")
    private String serverAddress;

    private CDMapper mapper = Mappers.getMapper(CDMapper.class);

    @Autowired
    private CDRepository cdRepository;

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
    public ResponseEntity<CdDTO> findOneAlbum(long id) {
        CdDTO cd = mapper.mapToCdDTO(cdRepository.findOneById(id));
        return new ResponseEntity<>(cd, HttpStatus.OK);
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
