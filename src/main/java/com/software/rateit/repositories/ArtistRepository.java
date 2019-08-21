package com.software.rateit.repositories;

import com.querydsl.core.types.dsl.StringPath;
import com.software.rateit.Entity.Artist;
import com.software.rateit.Entity.QArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface ArtistRepository extends JpaRepository<Artist,Long> , QuerydslPredicateExecutor<Artist>,
        QuerydslBinderCustomizer<QArtist>{

    Artist findOneById(long id);

    @Override
    default void customize(QuerydslBindings bindings, QArtist root){
        bindings.bind(root.stageName).first((path, value) -> path.contains(value));
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}
