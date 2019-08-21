package com.software.rateit.repositories;

import com.querydsl.core.types.dsl.StringPath;
import com.software.rateit.Entity.QTrack;
import com.software.rateit.Entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.Date;

public interface TrackRepository extends JpaRepository< Track, Long >, QuerydslPredicateExecutor<Track>,
        QuerydslBinderCustomizer<QTrack> {
    Track findByTitle(String title);
    Track findByReleaseDate(Date releaseDate);
    Track findOneById(long id);

    @Override
    default void customize(QuerydslBindings bindings, QTrack root){
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}
