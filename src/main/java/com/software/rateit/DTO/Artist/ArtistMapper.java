package com.software.rateit.DTO.Artist;

import com.software.rateit.Entity.Artist;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface ArtistMapper {

    @Named("WithoutCollections")
    @Mapping(ignore = true, target = "tracks")
    @Mapping(ignore = true, target = "cd")
    ArtistDTO mapToArtistDTO(Artist artist);

    @IterableMapping(qualifiedByName = "WithoutCollections")
    Iterable<ArtistDTO> mapToArtistDTOIterable(Iterable<Artist> artists);

    ArtistDTO mapToArtistDTOWithCollections(Artist artist);

    Artist mapToArtistEntity(ArtistDTO artistDTO);
}
