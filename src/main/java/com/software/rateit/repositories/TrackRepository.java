package com.software.rateit.repositories;

import com.software.rateit.Entity.Track;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface TrackRepository extends PagingAndSortingRepository< Track, Long > {
    Track findByTitle(String title);
    Track findByReleaseDate(Date releaseDate);
}
