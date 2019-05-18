package com.software.rateit.services;

public interface SecurityService {
    String findLoggedInUser();

    void autoLogin(String username, String password);
}
