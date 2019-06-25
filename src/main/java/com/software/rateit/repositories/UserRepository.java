package com.software.rateit.repositories;

import com.software.rateit.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    User findByNick(String nick);
    @Query("SELECT u FROM User u WHERE u.nick = :nick and u.password = :password")
    User findUserByNickAndPasswordNamedParams(
            @Param("nick") String name,
            @Param("password") String password);
    Iterable<User> findByBadges(String badges);
    Iterable<User> findByScore(int score);
    Iterable<User> findByRegistrationDate(Date registrationDate);
}
