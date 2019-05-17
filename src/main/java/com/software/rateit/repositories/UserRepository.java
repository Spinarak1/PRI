package com.software.rateit.repositories;

import com.software.rateit.Entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);
    User findByNick(String nick);
}
