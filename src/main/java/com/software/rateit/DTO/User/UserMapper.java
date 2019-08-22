package com.software.rateit.DTO.User;

import com.software.rateit.Entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface UserMapper {

    @Named("WithoutCollections")
    @Mapping(ignore = true, target = "userscd")
    @Mapping(ignore = true, target = "comments")
    UserDTO mapToUserDTO(User user);

    @IterableMapping(qualifiedByName = "WithoutCollections")
    Iterable<UserDTO> mapToUserDTOIterable(Iterable<User> users);

    @Mapping(ignore = true, target = "comments")
    @Mapping(ignore = true, target = "userscd")
    UserDTO mapToUserDTOWithCollections(User user);

}
