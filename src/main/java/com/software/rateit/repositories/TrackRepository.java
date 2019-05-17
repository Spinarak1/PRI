package com.software.rateit.repositories;

import com.software.rateit.Entity.Track;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrackRepository extends PagingAndSortingRepository< Track, Long > {
}
