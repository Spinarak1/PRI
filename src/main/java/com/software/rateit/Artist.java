package com.software.rateit;

import javax.persistence.*;

@Entity
@Table(name = "Artist")
public class Artist {
    @Id@GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "stageName")
    private String stageName;

    @ManyToMany
    private CD cd;
    @ManyToMany
    private Track track;

    public Artist(){}
    public Artist(int id, String stageName, CD cd, Track track){
        this.id = id;
        this.stageName = stageName;
        this.cd = cd;
        this.track = track;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
