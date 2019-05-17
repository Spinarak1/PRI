package com.software.rateit.services;

import com.software.rateit.Entity.User;
import com.software.rateit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findByNick(String nick) {
        return repository.findByNick(nick);
    }

    @Override
    public void registerNewUser(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public Boolean validateEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        String emailPattern = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
