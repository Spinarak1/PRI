package com.software.rateit.DTO.Rate;

import com.software.rateit.Entity.CD;

public class RateDTO {
    private CD album;
    private float note;

    public CD getAlbum() {
        return album;
    }

    public void setAlbum(CD album) {
        this.album = album;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
