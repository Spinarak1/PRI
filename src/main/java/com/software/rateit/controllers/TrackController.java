package com.software.rateit.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.Track.TrackDTO;
import com.software.rateit.DTO.Track.TrackWrapper;
import com.software.rateit.DTO.View;
import com.software.rateit.Entity.Track;
import com.software.rateit.services.Track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @JsonView(View.Summary.class)
    @GetMapping("/tracks")
    ResponseEntity<TrackWrapper> getAllTracks(@PageableDefault(value = 10, page = 0) Pageable pageable,
                                              @QuerydslPredicate(root = Track.class) Predicate predicate){
        return trackService.getAllTracks(predicate, pageable);
    }

    @GetMapping("/tracks/{id}")
    ResponseEntity<TrackDTO> getOneTrack(@PathVariable long id){
        return trackService.getOneTrack(id);
    }

    @PostMapping("/tracks")
    ResponseEntity<TrackDTO> addNewTrack(@RequestBody TrackDTO trackDTO) {
        return trackService.addTrack(trackDTO);
    }

    @PutMapping("/tracks/{id}")
    ResponseEntity<TrackDTO> editTrack(@PathVariable long id,
                                       @RequestBody TrackDTO trackDTO) {
        return trackService.editTrack(id, trackDTO);
    }

    @DeleteMapping("/tracks/{id}")
    ResponseEntity<Void> deleteTrack(@PathVariable long id) {
        return trackService.deleteTrack(id);
    }
}
