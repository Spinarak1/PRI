package com.software.rateit.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CD")
public class CD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int released;
    private float rating = 0;
    private String comment;
    private int ratingCount = 0;
    private float sumOfRating = 0;
    private String photoURL = "http://bobjames.com/wp-content/themes/soundcheck/images/default-album-artwork.png";
    private String artist;
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Track> cdtracks;
    private String genre;
    @JsonBackReference
    @ManyToMany(mappedBy = "userscd", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> user;

    @JsonBackReference
    @OneToMany(mappedBy = "cd", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> comments;

    public CD(){}

    public CD(String name, int released, String comment, int ratingCount, int sumOfRating, String photoURL, String artist, List<Track> track, String genre, List<User> user) {
        this.name = name;
        this.released = released;
        this.comment = comment;
        this.cdtracks = track;
        this.artist = artist;
        this.genre = genre;
        this.user = user;
        this.ratingCount = ratingCount;
        this.sumOfRating = sumOfRating;
        this.photoURL = photoURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public String getArtist() {
        return artist;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<Track> getCdtracks() {
        return cdtracks;
    }

    public void setCdtracks(List<Track> cdtracks) {
        this.cdtracks = cdtracks;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
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

    public String getPhotoURL() { return photoURL; }

    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public void addTrack(Track track) {
        this.getCdtracks().add(track);
    }

}
