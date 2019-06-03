package com.software.rateit.repositories;

import com.software.rateit.Entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);
    User findByNick(String nick);
    Iterable<User> findByBadges(String badges);
    Iterable<User> findByScore(int score);
    Iterable<User> findByRegistrationDate(Date registrationDate);
}
