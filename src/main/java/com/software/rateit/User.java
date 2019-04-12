package com.software.rateit;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id@GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "nick")
    private String nick;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "score")
    private int score;
    @Column(name = "badges")
    private String badges;

    @ManyToMany
    private CD cd;
    @OneToMany
    private User user;

    public User() {}
    public User(String nick, String email, String password, int score, String badges, CD cd, User user) {
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.score = score;
        this.badges = badges;
        this.cd = cd;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
