package com.software.rateit.controllers;

import com.software.rateit.User;
import com.software.rateit.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserRepository repository;
    @GetMapping("/users")
    Iterable<User> getAllUsers() {
        return repository.findAll();
    }
    @GetMapping("/users/{id}")
    User getUsersById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CouldNotFindException(id));
    }

    @PostMapping("/users")
    User newUsers(@RequestBody User newArtist){
        return repository.save(newArtist);
    }

    @PutMapping("/users/{id}")
    User replaceUsers(@RequestBody User newUser, @PathVariable Long id){
        return repository.findById(id)
                .map(user -> {
                    user.setBadges(newUser.getBadges());
                   // user.setCd(newUser.getCd());
                    user.setEmail(newUser.getEmail());
                    user.setNick(newUser.getNick());
                    user.setPassword(newUser.getPassword());
                    user.setScore(newUser.getScore());
                    return repository.save(user);
                })
                .orElseGet(() ->{
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUsers(@PathVariable Long id){
        repository.deleteById(id);
    }
}
