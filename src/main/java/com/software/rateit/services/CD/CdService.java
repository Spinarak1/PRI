package com.software.rateit.services.CD;

import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.CD.CDWithCommentsDTO;
import com.software.rateit.DTO.CD.CDWrapper;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.Track.NewTrackDTO;
import com.software.rateit.DTO.Track.TrackDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public interface CdService {
    ResponseEntity<CDWrapper> findAllAlbums(Pageable pageable, Predicate predicate);
    ResponseEntity<Iterable<CdDTO>> getRatingList();
    ResponseEntity<CDWithCommentsDTO> findOneAlbum(long id);
    ResponseEntity<CdDTO> updateAlbum(long id, CdDTO cdDTO);
    ResponseEntity<Void> deleteAlbum(long id);
    ResponseEntity<CdDTO> addNewAlbum(CdDTO cd );
    ResponseEntity<CdDTO> rateCD(long id, long userId, float note);
    ResponseEntity<CdDTO> addTrackToAlbum(long cdId, NewTrackDTO newTrackDTO);
    ResponseEntity<Iterable<TrackDTO>> getCdTracks(long id);
    ResponseEntity<CdDTO> addCdCover(MultipartFile file, long id);
}
