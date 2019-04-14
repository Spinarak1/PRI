package com.software.rateit.controllers;

import com.software.rateit.Track;
import com.software.rateit.repositories.TrackRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackController {

    private TrackRepository repository;

    @GetMapping("/artists")
    Iterable<Track> getAllArtists() {
        return repository.findAll();
    }
    @GetMapping("/artists/{id}")
    Track getTrackById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouldNotFindException(id));
    }

    @PostMapping("/artists")
    Track newTrack(@RequestBody Track newTrack){
        return repository.save(newTrack);
    }

    @PutMapping("/artists/{id}")
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

    @DeleteMapping("/artists/{id}")
    void deleteTrack(@PathVariable Long id){
        repository.deleteById(id);
    }
}
