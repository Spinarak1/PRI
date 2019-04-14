package com.software.rateit.controllers;

import com.software.rateit.Artist;
import com.software.rateit.repositories.ArtistRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArtistController {
    private ArtistRepository repository;

    @GetMapping("/artists")
    Iterable<Artist> getAllArtists() {
        return repository.findAll();
    }
    @GetMapping("/artists/{id}")
    Artist getArtistById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouldNotFindException(id));
    }

    @PostMapping("/artists")
    Artist newArtist(@RequestBody Artist newArtist){
        return repository.save(newArtist);
    }

    @PutMapping("/artists/{id}")
    Artist replaceArtist(@RequestBody Artist newArtist, @PathVariable Long id){
        return repository.findById(id)
                .map(artist -> {
                    artist.setStageName(newArtist.getStageName());
                    artist.setCd(newArtist.getCd());
                    artist.setTrack(newArtist.getTrack());
                    return repository.save(artist);
                })
                .orElseGet(() ->{
                    newArtist.setId(id);
                    return repository.save(newArtist);
                });
    }

    @DeleteMapping("/artists/{id}")
    void deleteArtist(@PathVariable Long id){
        repository.deleteById(id);
    }

}
