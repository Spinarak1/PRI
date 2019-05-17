package com.software.rateit.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Genre")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Genre{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CD> cd = new HashSet<>();

    @ManyToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Track> track;

    public Genre() {}
    public Genre(String type, Set<CD> cd, Set<Track> track) {
        this.type = type;
        this.cd = cd;
        this.track = track;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<CD> getCd() {
        return cd;
    }

    public void setCd(Set<CD> cd) {
        this.cd = cd;
    }

    public Set<Track> getTrack() {
        return track;
    }

    public void setTrack(Set<Track> track) {
        this.track = track;
    }
}
