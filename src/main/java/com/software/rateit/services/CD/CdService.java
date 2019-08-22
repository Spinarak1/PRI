package com.software.rateit.services.CD;

import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.CD.CDWithCommentsDTO;
import com.software.rateit.DTO.CD.CDWrapper;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.Track.TrackDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface CdService {
    ResponseEntity<CDWrapper> findAllAlbums(Pageable pageable, Predicate predicate);
    ResponseEntity<Iterable<CdDTO>> getRatingList();
    ResponseEntity<CDWithCommentsDTO> findOneAlbum(long id);
    ResponseEntity<CdDTO> updateAlbum(long id, CdDTO cdDTO);
    ResponseEntity<Void> deleteAlbum(long id);
    ResponseEntity<CdDTO> addNewAlbum(CdDTO cd);
    ResponseEntity<CdDTO> rateCD(long id, float note);
    ResponseEntity<CdDTO> addTrackToAlbum(long cdId, long trackId);
    ResponseEntity<Iterable<TrackDTO>> getCdTracks(long id);
}
