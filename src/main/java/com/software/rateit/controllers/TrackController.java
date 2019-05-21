package com.software.rateit.controllers;

import com.software.rateit.Entity.Track;
import com.software.rateit.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
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
    @GetMapping("/TrackByTitle")
    public Track findByTitle(
            @RequestParam("title") String title) {
        if(title != null)
            return repository.findByTitle(title);
        else
            throw new CouldNotFindException(title);
    }
    @GetMapping("/TrackByReleaseDate")
    public Track findTrackByReleaseDate(
            @RequestParam("releaseDate") Date releaseDate) {
        if(releaseDate != null)
            return repository.findByReleaseDate(releaseDate);
        else
            throw new CouldNotFindException(releaseDate);
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
