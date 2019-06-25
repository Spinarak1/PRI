package com.software.rateit.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.software.rateit.Role;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
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
    @Column(name = "registrationDate")
    private Date registrationDate = new Date();
    @Transient
    private String passwordConfirm;
    @Column
    private boolean isActive;
    @Column
    private String photoURL;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CD> userscd = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {}

    public User(String nick, String email, String password, int score, String badges, boolean isActive, String photoURL, Set<CD> cd, Collection<Role> role) {
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.score = score;
        this.badges = badges;
        this.userscd = cd;
        this.roles = role;
        this.isActive = isActive;
        this.photoURL = photoURL;
    }

    public User(Long id, String nick, String email, String password, int score, String badges, boolean isActive, String photoURL, Set<CD> cd, Collection<Role> role) {
        this.id = id;
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.score = score;
        this.badges = badges;
        this.userscd = cd;
        this.roles = role;
        this.isActive = isActive;
        this.photoURL = photoURL;
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

    public void addCd(CD userscd){
        getUserscd().add(userscd);
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
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

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
