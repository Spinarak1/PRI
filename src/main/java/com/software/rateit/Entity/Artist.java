package com.software.rateit.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String stageName;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CD> cd;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Track> tracks;

    public Artist(){}

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

    public List<CD> getCd() {
        return cd;
    }

    public void setCd(List<CD> cd) {
        this.cd = cd;
    }

    public List<Track> getTrack() {
        return tracks;
    }

    public void setTrack(List<Track> track) {
        this.tracks = track;
    }

    public void addCD(CD cd){
        this.getCd().add(cd);
    }
}
