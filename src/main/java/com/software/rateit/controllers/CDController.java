package com.software.rateit.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.CD.CDWithCommentsDTO;
import com.software.rateit.DTO.CD.CDWrapper;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.Comments.CommentAlbumDTO;
import com.software.rateit.DTO.Comments.CommentsDTO;
import com.software.rateit.DTO.Track.NewTrackDTO;
import com.software.rateit.DTO.Track.TrackDTO;
import com.software.rateit.DTO.View;
import com.software.rateit.Entity.CD;
import com.software.rateit.Entity.Comments;
import com.software.rateit.services.CD.CdService;
import com.software.rateit.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CDController {

    @Autowired
    private CdService cdService;

    @Autowired
    private UserService userService;

    @JsonView(View.Summary.class)
    @GetMapping("/cds")
    public ResponseEntity<CDWrapper> getAllCDs(@PageableDefault(value = 10, page = 0) Pageable pageable,
                                        @QuerydslPredicate(root = CD.class) Predicate predicate){
        return cdService.findAllAlbums(pageable, predicate);
    }

    @GetMapping("/cds/ranking")
    public ResponseEntity<Iterable<CdDTO>> getRanking(){
        return cdService.getRatingList();
    }

    @JsonView(View.Comment.class)
    @GetMapping("/cds/{id}")
    public ResponseEntity<CDWithCommentsDTO> getCdById(@PathVariable long id){
        return cdService.findOneAlbum(id);
    }

    @GetMapping("/cds/{id}/tracks")
    public ResponseEntity<Iterable<TrackDTO>> getCDTracks(@PathVariable long id){
        return cdService.getCdTracks(id);
    }

    @PostMapping("/cds")
    public ResponseEntity<CdDTO> addNewCD(@RequestBody CdDTO cdDTO){
        return cdService.addNewAlbum(cdDTO);
    }

    @PostMapping("/cds/{id}/add-image")
    public ResponseEntity<CdDTO> addNewCD(@PathVariable long id,
                                          @RequestParam("file") MultipartFile file){
        return cdService.addCdCover(file, id);
    }

    @PostMapping("/cds/{id}/rate")
    public ResponseEntity<CdDTO> rateCD(@PathVariable long id,
                                        @RequestParam long userId,
                                        @RequestParam float note){
        return cdService.rateCD(id, userId, note);
    }

    @JsonView(View.Comment.class)
    @PostMapping("/cds/{id}/add-comment")
    public ResponseEntity<CommentsDTO> addCommentToAlbum(@PathVariable long id,
                                                         @RequestBody CommentAlbumDTO commentAlbumDTO){
        return userService.commentAlbum(commentAlbumDTO, id);
    }

    @PostMapping("/cds/{id}/add-track")
    public ResponseEntity<CdDTO> addTrackToAlbum(@PathVariable long id,
                                                 @RequestBody NewTrackDTO newTrackDTO){
        return cdService.addTrackToAlbum(id, newTrackDTO);
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

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentsDTO> editComment(@PathVariable long id, @RequestBody CommentAlbumDTO commentAlbumDTO){
        return userService.editComment(commentAlbumDTO, id);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id, @RequestParam long userID){
        return userService.deleteComment(id, userID);
    }
}
