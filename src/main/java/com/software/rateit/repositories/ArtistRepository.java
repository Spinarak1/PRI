package com.software.rateit.repositories;

import com.software.rateit.Entity.Artist;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArtistRepository extends PagingAndSortingRepository<Artist,Long> {
    Artist findByStageName(String stageName);
    Artist findByCdName(String name);

}
