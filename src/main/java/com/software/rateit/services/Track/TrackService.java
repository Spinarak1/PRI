package com.software.rateit.services.Track;

import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.Track.TrackDTO;
import com.software.rateit.DTO.Track.TrackWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TrackService {
    ResponseEntity<TrackDTO> getOneTrack(long id);
    ResponseEntity<TrackWrapper> getAllTracks(Predicate predicate, Pageable pageable);
    ResponseEntity<TrackDTO> addTrack(TrackDTO trackDTO);
    ResponseEntity<Void> deleteTrack(long id);
    ResponseEntity<TrackDTO> editTrack(long id, TrackDTO trackDTO);
}
