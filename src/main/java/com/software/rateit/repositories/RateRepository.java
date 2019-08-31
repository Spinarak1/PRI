package com.software.rateit.repositories;

import com.software.rateit.Entity.Rate;
import org.mapstruct.IterableMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findOneByCdIdAndUserId(long cdId, long userId);
    Iterable<Rate> findAllByUserId(long userId);
}
