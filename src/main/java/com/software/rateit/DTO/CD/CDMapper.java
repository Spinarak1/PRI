package com.software.rateit.DTO.CD;

import com.software.rateit.Entity.CD;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface CDMapper {

    @Named("WithoutCollections")
    @Mapping(ignore = true, target = "cdtracks")
    @Mapping(ignore = true, target = "user")
    @Mapping(ignore = true, target = "comments")
    CdDTO mapToCdDTO(CD cd);

    @IterableMapping(qualifiedByName = "WithoutCollections")
    Iterable<CdDTO> mapToCdDTOIterable(Iterable<CD> cds);

    @Named("pls")
    @Mapping(ignore = true, target = "comments")
    CdDTO mapToCdDTOWithCollections(CD cd);

    @IterableMapping(qualifiedByName = "pls")
    CD mapToCd(CdDTO cdDTO);
}
