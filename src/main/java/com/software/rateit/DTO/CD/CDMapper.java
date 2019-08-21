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
    CdDTO mapToCdDTO(CD cd);

    @IterableMapping(qualifiedByName = "WithoutCollections")
    Iterable<CdDTO> mapToCdDTOIterable(Iterable<CD> cds);

    CdDTO mapToCdDTOWithCollections(CD cd);

    CD mapToCd(CdDTO cdDTO);
}
