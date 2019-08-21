package com.software.rateit.DTO.Track;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.PaginationContext;
import com.software.rateit.DTO.View;

@JsonView(View.Summary.class)
public class TrackWrapper {

    private Iterable<TrackDTO> tracks;
    private PaginationContext paginationContext;

    public TrackWrapper(Iterable<TrackDTO> tracks, PaginationContext paginationContext) {
        this.tracks = tracks;
        this.paginationContext = paginationContext;
    }

    public Iterable<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(Iterable<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public PaginationContext getPaginationContext() {
        return paginationContext;
    }

    public void setPaginationContext(PaginationContext paginationContext) {
        this.paginationContext = paginationContext;
    }
}
