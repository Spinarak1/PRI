package com.software.rateit.repositories;

import com.software.rateit.Entity.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenreRepository extends PagingAndSortingRepository<Genre,Long> {
}
