package com.software.rateit.services;

import com.software.rateit.Entity.User;

public interface UserService {
    User findByEmail(String email);
    User findByNick(String nick);
    void registerNewUser(User user);
    Boolean validateEmail(String email);
}
