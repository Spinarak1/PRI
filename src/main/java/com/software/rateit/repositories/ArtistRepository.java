package com.software.rateit.repositories;

import com.software.rateit.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
    Page<Artist> findByStageName(String stageName, Pageable pageable);
}
