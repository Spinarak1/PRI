package com.software.rateit.Entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CD")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class CD {
    @Id
    /*@SequenceGenerator(name = "mySeqGen", sequenceName = "myDbSeq",
            initialValue = 10000, allocationSize = 1)
    @GeneratedValue(generator = "mySeqGen")*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "released")
    private int released;
    @Column(name = "rating")
    private float rating;
    @Column(name = "comment")
    private String comment;
    @Column(name = "ratingCount")
    private int ratingCount;
    @Column(name = "sumOfRating")
    private int sumOfRating;
    @Column(name = "photoURL")
    private String photoURL;
    @Column(name="artist")
    private String artist;
    /*@ManyToMany(mappedBy = "cd", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Artist> artist = new HashSet<>();*/
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Track> cdtracks = new HashSet<>();
    private String genre;
    @JsonIgnore
    @ManyToMany(mappedBy = "userscd", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> user = new HashSet<>();

    public CD(){}

    public CD(String name, int released, String comment, int ratingCount, int sumOfRating, String photoURL, String artist, Set<Track> track, String genre, Set<User> user) {
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

    public Set<Track> getCdtracks() {
        return cdtracks;
    }

    public void setCdtracks(Set<Track> cdtracks) {
        this.cdtracks = cdtracks;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getSumOfRating() {
        return sumOfRating;
    }

    public void setSumOfRating(int sumOfRating) {
        this.sumOfRating = sumOfRating;
    }

    public String getPhotoURL() { return photoURL; }

    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }

}
