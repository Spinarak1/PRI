package com.software.rateit;

import javax.persistence.GeneratedValue;

public class Track {
    @GeneratedValue
    private int id;
    private String title;
    private String release_date;
    //@ManyToMany
    private Artist artist;
    //@ManyToMany
    private CD cd;

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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
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
