package com.software.rateit.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.Artist.ArtistDTO;
import com.software.rateit.DTO.Artist.ArtistWrapper;
import com.software.rateit.DTO.View;
import com.software.rateit.Entity.Artist;
import com.software.rateit.services.Artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @JsonView(View.Summary.class)
    @GetMapping("/artists")
    public ResponseEntity<ArtistWrapper> getAllArtist(@PageableDefault(value = 10, page = 0) Pageable pageable,
                                                      @QuerydslPredicate(root = Artist.class) Predicate predicate
    ) {
        return artistService.getAllArtists(predicate, pageable);

    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<ArtistDTO> getOneArtist(@PathVariable long id){
        return artistService.getOneArtist(id);
    }

    @PostMapping("/artists")
    ResponseEntity<ArtistDTO> addArtist(@RequestBody ArtistDTO artistDTO) {
        return artistService.addNewArtist(artistDTO);
    }

    @PutMapping("/artists/{id}")
    ResponseEntity<ArtistDTO> updateArtist(@PathVariable long id,
                                           @RequestBody ArtistDTO artistDTO) {
        return artistService.updateArtist(artistDTO, id);
    }

    @DeleteMapping("/artists/{id}")
    ResponseEntity<Void> removeArtist(@PathVariable long id) {
        return artistService.deleteArtist(id);
    }

}
