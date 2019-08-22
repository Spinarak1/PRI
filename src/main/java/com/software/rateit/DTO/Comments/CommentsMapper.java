package com.software.rateit.DTO.Comments;

import com.software.rateit.Entity.Comments;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.*;

@Mapper
public interface CommentsMapper {
    @Named("NoCd")
    @Mapping(ignore = true, target = "cd")
    @Mapping(ignore = true, target = "user")
    CommentsDTO mapToCommentsDTO(Comments comments);
    @IterableMapping(qualifiedByName = "NoCd")
    List<CommentsDTO> mapToCommentsDTOIterable(List<Comments> comments);
    @IterableMapping(qualifiedByName = "NoCd")
    List<Comments> mapToCommentsIterable(List<CommentsDTO> commentsDTOs);
    @IterableMapping(qualifiedByName = "NoCd")
    Comments mapToComments(CommentsDTO commentsDTO);



}
