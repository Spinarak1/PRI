package com.software.rateit.services;

import com.software.rateit.Entity.CD;
import com.software.rateit.Entity.User;
import com.software.rateit.controllers.CouldNotFindException;
import com.software.rateit.repositories.CDRepository;
import com.software.rateit.repositories.RoleRepository;
import com.software.rateit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CDRepository cdRepository;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        user.setPassword(user.getPassword());
        user.setRoles(new HashSet<>(roleRepository.findAll()));
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

    @Override
    public void changePassword(User user, String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        repository.save(user);
    }

    @Override
    public Boolean checkIfOldPasswordMatches(User user, String oldpassword) {
        return bCryptPasswordEncoder.matches(oldpassword, user.getPassword());
    }

    @Override
    public void addCdtoUser(Long userId, Long CdId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new CouldNotFindException(userId));
        CD cd = cdRepository.findById(CdId)
                .orElseThrow(() -> new CouldNotFindException(CdId));
        user.getUserscd().add(cd);
        repository.save(user);
    }


    @Override
    public Iterable<CD> getUsersCds(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new CouldNotFindException(userId));

        return user.getUserscd();

    }
    //wtf
}
