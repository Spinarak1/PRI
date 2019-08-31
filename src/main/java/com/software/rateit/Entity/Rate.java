package com.software.rateit.Entity;

import javax.persistence.*;

@Entity
public class Rate {

    @Id
    @GeneratedValue
    private long id;

    private float rating;
    private long cdId;
    private long userId;

    public Rate(float rating, long cdId, long userId) {
        this.rating = rating;
        this.cdId = cdId;
        this.userId = userId;
    }

    public Rate() {
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public long getCd() {
        return cdId;
    }

    public void setCd(long cd) {
        this.cdId = cd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
