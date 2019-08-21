package com.software.rateit.DTO.User;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.PaginationContext;
import com.software.rateit.DTO.View;

@JsonView(View.Summary.class)
public class UserResponseWrapper {
    private Iterable<UserDTO> users;
    private PaginationContext paginationContext;

    public UserResponseWrapper(Iterable<UserDTO> users, PaginationContext paginationContext) {
        this.users = users;
        this.paginationContext = paginationContext;
    }

    public Iterable<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Iterable<UserDTO> users) {
        this.users = users;
    }

    public PaginationContext getPaginationContext() {
        return paginationContext;
    }

    public void setPaginationContext(PaginationContext paginationContext) {
        this.paginationContext = paginationContext;
    }
}
