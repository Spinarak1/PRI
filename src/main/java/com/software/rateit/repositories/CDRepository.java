package com.software.rateit.repositories;

import com.querydsl.core.types.dsl.StringPath;
import com.software.rateit.Entity.CD;
import com.software.rateit.Entity.QCD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public interface CDRepository extends JpaRepository<CD,Long> , QuerydslPredicateExecutor<CD>,
        QuerydslBinderCustomizer<QCD> {

    CD findOneById(long id);
    CD findByName(String name);
    CD findByReleased(int released);
    Iterable<CD> findTop25ByRatingLessThanOrderByRatingDesc(float rating);

    @Override
    default void customize(QuerydslBindings bindings, QCD root){
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.bind(root.released).all(((path, value) -> {
            List<? extends Integer> dates = new ArrayList<>(value);
            if (dates.size() == 1) {
                return Optional.of(path.eq(dates.get(0)));
            } else {
                Integer from = dates.get(0);
                Integer to = dates.get(1);
                return Optional.of(path.between(from, to));
            }
        }));
    }

}
