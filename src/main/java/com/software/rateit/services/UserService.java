package com.software.rateit.services;

import com.software.rateit.Entity.User;

public interface UserService {
    User findByEmail(String email);
    User findByNick(String nick);
    void registerNewUser(User user);
    Boolean validateEmail(String email);
    void changePassword(User user, String password);
    Boolean checkIfOldPasswordMatches(User user, String oldpassword);
    void addCdtoUser(Long userId, Long CdId);
}
