package com.software.rateit.DTO.CD;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.Comments.CommentsDTO;
import com.software.rateit.DTO.Track.TrackDTO;
import com.software.rateit.DTO.User.UserDTO;
import com.software.rateit.DTO.View;

import java.util.List;

@JsonView(View.Comment.class)
public class CdDTO {

    @JsonView({View.Summary.class, View.Comment.class})
    private long id;
    @JsonView({View.Summary.class, View.Comment.class})
    private String name;
    @JsonView(View.Summary.class)
    private int released;
    @JsonView(View.Summary.class)
    private float rating;
    private String comment;
    @JsonIgnore
    private int ratingCount;
    @JsonIgnore
    private float sumOfRating;
    @JsonView(View.Summary.class)
    private String photoURL;
    @JsonView(View.Summary.class)
    private String artist;
    private List<TrackDTO> cdtracks;
    private String genre;
    private List<UserDTO> user;
    @JsonBackReference
    private List<CommentsDTO> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public float getSumOfRating() {
        return sumOfRating;
    }

    public void setSumOfRating(float sumOfRating) {
        this.sumOfRating = sumOfRating;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<TrackDTO> getCdtracks() {
        return cdtracks;
    }

    public void setCdtracks(List<TrackDTO> cdtracks) {
        this.cdtracks = cdtracks;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<UserDTO> getUser() {
        return user;
    }

    public void setUser(List<UserDTO> user) {
        this.user = user;
    }

    public List<CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentsDTO> comments) {
        this.comments = comments;
    }
}
