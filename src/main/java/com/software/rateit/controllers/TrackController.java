package com.software.rateit.controllers;

import com.software.rateit.Entity.Track;
import com.software.rateit.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class TrackController {

    @Autowired
    private TrackRepository repository;

    @GetMapping("/tracks")
    Iterable<Track> getAllTracks() {
        return repository.findAll();
    }
    @GetMapping("/tracks/{id}")
    Track getTrackById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouldNotFindException(id));
    }

    @PostMapping("/tracks")
    Track newTrack(@RequestBody Track newTrack){
        return repository.save(newTrack);
    }

    @PutMapping("/tracks/{id}")
    Track replaceTrack(@RequestBody Track newTrack, @PathVariable Long id){
        return repository.findById(id)
                .map(track -> {
                    track.setArtist(newTrack.getArtist());
                    track.setCd(newTrack.getCd());
                    track.setReleaseDate(newTrack.getReleaseDate());
                    track.setTitle(newTrack.getTitle());
                    return repository.save(track);
                })
                .orElseGet(() ->{
                    newTrack.setId(id);
                    return repository.save(newTrack);
                });
    }

    @DeleteMapping("/tracks/{id}")
    void deleteTrack(@PathVariable Long id){
        repository.deleteById(id);
    }
}
