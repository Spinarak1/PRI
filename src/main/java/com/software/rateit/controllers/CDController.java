package com.software.rateit.controllers;

import com.software.rateit.CD;
import com.software.rateit.repositories.CDRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class CDController {
    private CDRepository repository;

    @GetMapping("/CDs")
    Iterable<CD> getAllCDs() {
        return repository.findAll();
    }

    @GetMapping("/CDs/{id}")
    CD getCDById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouldNotFindException(id));
    }

    @PostMapping("/CDs")
    CD newCD(@RequestBody CD newArtist) {
        return repository.save(newArtist);
    }

    @PutMapping("/CDs/{id}")
    CD replaceCD(@RequestBody CD newCD, @PathVariable Long id) {
        return repository.findById(id)
                .map(cd -> {
                    cd.setArtist(newCD.getArtist());
                    cd.setGenres(newCD.getGenres());
                    cd.setName(newCD.getName());
                    cd.setReleased(newCD.getReleased());
                    cd.setCdtracks(newCD.getCdtracks());
                    return repository.save(cd);
                })
                .orElseGet(() -> {
                    newCD.setId(id);
                    return repository.save(newCD);
                });
    }

    @DeleteMapping("/CDs/{id}")
    void deleteCD(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
