package com.software.rateit.DTO.Comments;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.User.UserDTO;
import com.software.rateit.DTO.View;

@JsonView(View.Comment.class)
public class CommentsDTO {

    private long id;
    private String content;
    private CdDTO cd;
    private UserDTO user;

    public CommentsDTO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CdDTO getCd() {
        return cd;
    }

    public void setCd(CdDTO cd) {
        this.cd = cd;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
