package com.software.rateit.controllers;

import com.software.rateit.Entity.Genre;
import com.software.rateit.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class GenreController {

    @Autowired
    private GenreRepository repository;

    @GetMapping(value = "/genres", produces = MediaType.APPLICATION_JSON_VALUE)
    Iterable<Genre> getAllGenres() {
        return repository.findAll();
    }

    @GetMapping("/genres/{id}")
    Genre getGenreById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouldNotFindException(id));
    }

    @PostMapping("/genres")
    Genre newGenre(@RequestBody Genre newGenre){
        return repository.save(newGenre);
    }

    @PutMapping("/genres/{id}")
    Genre replaceArtist(@RequestBody Genre newGenre, @PathVariable Long id){
        return repository.findById(id)
                .map(genre -> {
                    genre.setCd(newGenre.getCd());
                    genre.setTrack(newGenre.getTrack());
                    genre.setType(newGenre.getType());
                    return repository.save(genre);

                })
                .orElseGet(() ->{
                    newGenre.setId(id);
                    return repository.save(newGenre);
                });
    }

    @DeleteMapping("/genres/{id}")
    void deleteGenre(@PathVariable Long id){
        repository.deleteById(id);
    }
}
