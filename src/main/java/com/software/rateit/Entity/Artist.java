package com.software.rateit.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Artist")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "stageName")
    private String stageName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CD> cd = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Track> tracks = new HashSet<>();

    public Artist(){}
    public Artist(Long id, String stageName, Set<CD> cd, Set<Track> track){
        this.id = id;
        this.stageName = stageName;
        this.tracks = track;
        this.cd = cd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Set<CD> getCd() {
        return cd;
    }

    public void setCd(Set<CD> cd) {
        this.cd = cd;
    }

    public Set<Track> getTrack() {
        return tracks;
    }

    public void setTrack(Set<Track> track) {
        this.tracks = track;
    }
}
