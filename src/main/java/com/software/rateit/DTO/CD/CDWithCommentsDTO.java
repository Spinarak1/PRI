package com.software.rateit.DTO.CD;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.Comments.CommentsDTO;
import com.software.rateit.DTO.View;

import java.util.*;

@JsonView(View.Comment.class)
public class CDWithCommentsDTO {
    private CdDTO cd;
    private List<CommentsDTO> comments;

    public CdDTO getCd() {
        return cd;
    }

    public void setCd(CdDTO cd) {
        this.cd = cd;
    }

    public List<CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentsDTO> comments) {
        this.comments = comments;
    }
}
