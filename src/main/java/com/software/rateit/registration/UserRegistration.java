package com.software.rateit.registration;

public class UserRegistration {

    private String nick;
    private String password;
    private String passwordConfirmation;

    public UserRegistration() {
    }

    public UserRegistration(String nick, String password, String passwordConfirmation) {
        this.nick = nick;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}