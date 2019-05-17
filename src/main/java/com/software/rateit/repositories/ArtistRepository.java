package com.software.rateit.repositories;

import com.software.rateit.Entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRepository extends PagingAndSortingRepository<Artist,Long> {
    Page<Artist> findByStageName(String stageName, Pageable pageable);
}
