package com.software.rateit.DTO.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.Comments.CommentsDTO;
import com.software.rateit.DTO.View;

import java.util.List;
import java.util.Set;

public class UserDTO {
    @JsonView({View.Summary.class, View.Comment.class})
    private Long id;
    @JsonView({View.Summary.class, View.Comment.class})
    private String nick;
    @JsonView(View.Summary.class)
    private String email;
    @JsonIgnore
    private String password;
    private int score;
    private String badges;
    private Long registrationDate;
    private boolean isActive;
    @JsonView(View.Summary.class)
    private String photoURL;
    private Set<CdDTO> userscd;
    @JsonView(View.Summary.class)
    private String role;
    @JsonIgnore
    private List<CommentsDTO> comments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getBadges() {
        return badges;
    }

    public void setBadges(String badges) {
        this.badges = badges;
    }

    public Long getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Long registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Set<CdDTO> getUserscd() {
        return userscd;
    }

    public void setUserscd(Set<CdDTO> userscd) {
        this.userscd = userscd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentsDTO> comments) {
        this.comments = comments;
    }
}
