package com.software.rateit.repositories;

import com.software.rateit.Entity.CD;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface CDRepository extends CrudRepository<CD,Long> {
    CD findByName(String name);
    CD findByReleased(int released);
    Iterable<CD> findTop25ByRatingLessThanOrderByRatingDesc(float rating);

}
