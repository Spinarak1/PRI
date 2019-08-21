package com.software.rateit.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Integer releaseDate;
    @JsonBackReference
    @ManyToMany(mappedBy = "tracks",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Artist> artist;
    @JsonBackReference
    @ManyToMany(mappedBy = "cdtracks",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CD> cd;
    private String genre;

    public Track() {}
    public Track(String title, int releaseDate, List<Artist> artist, List<CD> cd, String genre) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.cd = cd;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public List<CD> getCd() {
        return cd;
    }

    public void setCd(List<CD> cd) {
        this.cd = cd;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
