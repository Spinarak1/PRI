package com.software.rateit.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Comments {

    @Id
    @GeneratedValue
    private long id;

    private String content;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private CD cd;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
