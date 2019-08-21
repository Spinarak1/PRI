package com.software.rateit.controllers;

import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.CD.CDWrapper;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.Entity.CD;
import com.software.rateit.services.CD.CdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CDController {

    @Autowired
    private CdService cdService;

    @GetMapping("/cds")
    public ResponseEntity<CDWrapper> getAllCDs(@PageableDefault(value = 10, page = 0) Pageable pageable,
                                        @QuerydslPredicate(root = CD.class) Predicate predicate){
        return cdService.findAllAlbums(pageable, predicate);
    }

    @GetMapping("/cds/ranking")
    public ResponseEntity<Iterable<CdDTO>> getRanking(){
        return cdService.getRatingList();
    }

    @GetMapping("/cds/{id}")
    public ResponseEntity<CdDTO> getCdById(@PathVariable long id){
        return cdService.findOneAlbum(id);
    }

    @PostMapping("/cds")
    public ResponseEntity<CdDTO> addNewCD(@RequestBody CdDTO cdDTO){
        return cdService.addNewAlbum(cdDTO);
    }

    @PostMapping("/cds/{id}/rate")
    public ResponseEntity<CdDTO> rateCD(@PathVariable long id,
                                        @RequestParam float note){
        return cdService.rateCD(id, note);
    }

    @PutMapping("/cds/{id}")
    public ResponseEntity<CdDTO> updateCD(@PathVariable long id,
                                   @RequestBody CdDTO cdDTO){
        return cdService.updateAlbum(id, cdDTO);
    }

    @DeleteMapping("/cds/{id}")
    public ResponseEntity<Void> deleteCD(@PathVariable long id){
        return cdService.deleteAlbum(id);
    }
}
