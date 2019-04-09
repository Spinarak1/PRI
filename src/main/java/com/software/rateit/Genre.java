package com.software.rateit;

import javax.persistence.GeneratedValue;

public class Genre {
    @GeneratedValue
    private int id;
    private String type;
    //@ManyToMany
    private CD cd;
    //@ManyToMany
    private Track track;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
