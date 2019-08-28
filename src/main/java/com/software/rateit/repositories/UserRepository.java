package com.software.rateit.repositories;

import com.software.rateit.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);
    User findOneByNick(String nick);
    User findOneById(long id);
    List<User> findByRegistrationDateGreaterThanOrderByRegistrationDateDesc(long date);
}
