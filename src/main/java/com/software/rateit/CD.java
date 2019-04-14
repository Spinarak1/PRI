package com.software.rateit;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CD")
public class CD {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "released")
    private Date released = new Date();

    @ManyToMany(mappedBy = "cd")
    private Set<Artist> artist = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cd_track",
            joinColumns = @JoinColumn(name = "cd_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "track_id", referencedColumnName = "id"))
    private Set<Track> cdtracks = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cd_genre",
            joinColumns = @JoinColumn(name = "cd_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private Set<Genre> genres = new HashSet<>();

   @ManyToMany(mappedBy = "userscd")
    private Set<User> user = new HashSet<>();

    public CD(){}
    public CD(String name, Date released, Set<Artist> artist, Set<Track> track, Set<Genre> genre, Set<User> user) {
        this.name = name;
        this.released = released;
        this.cdtracks = track;
        this.artist = artist;
        this.genres = genre;
        this.user = user;
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

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public Set<Artist> getArtist() {
        return artist;
    }

    public void setArtist(Set<Artist> artist) {
        this.artist = artist;
    }

    public Set<Track> getCdtracks() {
        return cdtracks;
    }

    public void setCdtracks(Set<Track> cdtracks) {
        this.cdtracks = cdtracks;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}
