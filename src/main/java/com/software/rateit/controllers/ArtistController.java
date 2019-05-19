package com.software.rateit.controllers;

import com.software.rateit.Entity.Artist;
import com.software.rateit.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class ArtistController {

    @Autowired
    private ArtistRepository repository;

    @GetMapping("/artists")
    Iterable<Artist> getArtists() {
        return repository.findAll();
    }

    @GetMapping("/artists/{id}")
    Artist getOneArtistById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouldNotFindException(id));
    }
    @GetMapping("/artistsByName")
    public Artist findArtistByName(
            @RequestParam("stageName") String stageName) {
        if(stageName != null)
            return repository.findByStageName(stageName);
        else
            throw new CouldNotFindException(stageName);
    }
    /*@GetMapping("/artistsByCdName")
    public Artist findArtistByCdName(
            @RequestParam("cdName") String name) {
        if(name != null)
            return repository.findByCdName(name);
        else
            throw new CouldNotFindException(stageName);
    }*/

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
