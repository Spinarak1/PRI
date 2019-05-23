package com.software.rateit.repositories;

import com.software.rateit.Entity.CD;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface CDRepository extends PagingAndSortingRepository<CD,Long> {
    CD findByName(String name);
    CD findByReleased(Date released);
}
