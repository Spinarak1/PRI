package com.software.rateit;

import javax.persistence.*;

@Entity
@Table(name = "Track")
public class Track {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "releaseDate")
    private String releaseDate;

    @ManyToMany
    private Artist artist;
    @ManyToMany
    private CD cd;

    public Track() {}
    public Track(String title, String releaseDate, Artist artist, CD cd) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.cd = cd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String release_date) {
        this.releaseDate = releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }
}
