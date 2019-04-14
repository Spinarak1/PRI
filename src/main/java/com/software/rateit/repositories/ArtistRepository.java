package com.software.rateit.repositories;

import com.software.rateit.Artist;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRepository extends PagingAndSortingRepository<Artist,Long> {
}
