package com.software.rateit;

import javax.persistence.*;

@Entity
@Table(name = "CD")
public class CD {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "released")
    private String released;

    @ManyToMany
    private Artist artist;
    @ManyToMany
    private Track track;
    @ManyToMany
    private Genre genre;

    public CD(){}
    public CD(String name, String released, Artist artist, Track track, Genre genre) {
        this.name = name;
        this.released = released;
        this.artist = artist;
        this.track = track;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
