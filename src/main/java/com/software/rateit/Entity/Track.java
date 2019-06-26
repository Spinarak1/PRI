package com.software.rateit.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Track")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Track {
    @Id
   /* @SequenceGenerator(name = "mySeqGen", sequenceName = "myDbSeq",
            initialValue = 131000, allocationSize = 1)
    @GeneratedValue(generator = "mySeqGen")*/
   @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "releaseDate")
    private int releaseDate;
    @JsonIgnore
    @ManyToMany(mappedBy = "tracks")
    private Set<Artist> artist = new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "cdtracks")
    private Set<CD> cd = new HashSet<>();
    private String genre;

    public Track() {}
    public Track(String title, int releaseDate, Set<Artist> artist, Set<CD> cd, String genre) {
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

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Artist> getArtist() {
        return artist;
    }

    public void setArtist(Set<Artist> artist) {
        this.artist = artist;
    }

    public Set<CD> getCd() {
        return cd;
    }

    public void setCd(Set<CD> cd) {
        this.cd = cd;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
