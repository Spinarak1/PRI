package com.software.rateit.repositories;

import com.software.rateit.Track;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TrackRepository extends PagingAndSortingRepository< Track, Long > {
}
