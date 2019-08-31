package com.software.rateit.DTO.Track;

import com.software.rateit.Entity.Track;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface TrackMapper {

    @Named("WithoutCollections")
    //@Mapping(ignore = true, target = "artist")
    @Mapping(ignore = true, target = "cd")
    TrackDTO mapToTrackDTO(Track track);

    @IterableMapping(qualifiedByName = "WithoutCollections")
    Iterable<TrackDTO> mapToTrackDTOIterable(Iterable<Track> tracks);

    TrackDTO mapToTrackDTOWithCollections(Track track);

    Track mapToTrack(TrackDTO trackDTO);
}
