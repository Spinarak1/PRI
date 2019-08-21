package com.software.rateit.DTO.Artist;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.PaginationContext;
import com.software.rateit.DTO.View;

@JsonView(View.Summary.class)
public class ArtistWrapper {

    Iterable<ArtistDTO> artists;
    PaginationContext context;

    public ArtistWrapper(Iterable<ArtistDTO> artists, PaginationContext context) {
        this.artists = artists;
        this.context = context;
    }

    public Iterable<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(Iterable<ArtistDTO> artists) {
        this.artists = artists;
    }

    public PaginationContext getContext() {
        return context;
    }

    public void setContext(PaginationContext context) {
        this.context = context;
    }
}
