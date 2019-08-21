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
    UserDTO mapToUserDTO(User user);

    @IterableMapping(qualifiedByName = "WithoutCollections")
    Iterable<UserDTO> mapToUserDTOIterable(Iterable<User> users);

    UserDTO mapToUserDTOWithCollections(User user);

}
