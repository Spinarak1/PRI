package com.software.rateit.DTO.Rate;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.View;
@JsonView({View.Summary.class})
public class RateDTO {
    private CdDTO album;
    private float note;



    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public CdDTO getAlbum() {
        return album;
    }

    public void setAlbum(CdDTO album) {
        this.album = album;
    }
}
