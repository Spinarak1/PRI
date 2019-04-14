package com.software.rateit;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_cd",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cd_id", referencedColumnName = "id"))
    private Set<CD> userscd = new HashSet<>();

    public User() {}
    public User(String nick, String email, String password, int score, String badges, Set<CD> cd) {
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.score = score;
        this.badges = badges;
        this.userscd = cd;
    }

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

    public Set<CD> getUserscd() {
        return userscd;
    }

    public void setUserscd(Set<CD> userscd) {
        this.userscd = userscd;
    }
}
