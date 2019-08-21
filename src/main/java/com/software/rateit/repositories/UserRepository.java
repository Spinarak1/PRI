package com.software.rateit.repositories;

import com.software.rateit.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);
    User findOneByNick(String nick);
    User findOneById(long id);
}
