package com.software.rateit.repositories;

import com.software.rateit.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Comments findOneById(long id);
}
