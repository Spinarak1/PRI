package com.software.rateit.repositories;

import com.software.rateit.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenreRepository extends PagingAndSortingRepository<Genre,Long> {
}
