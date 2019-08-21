package com.software.rateit.DTO.Track;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.Artist.ArtistDTO;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.View;

import java.util.Set;

public class TrackDTO {

    @JsonView(View.Summary.class)
    private long id;
    @JsonView(View.Summary.class)
    private String title;
    @JsonView(View.Summary.class)
    private Integer releaseDate;
    private Set<ArtistDTO> artist;
    private Set<CdDTO> cd;
    private String genre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<ArtistDTO> getArtist() {
        return artist;
    }

    public void setArtist(Set<ArtistDTO> artist) {
        this.artist = artist;
    }

    public Set<CdDTO> getCd() {
        return cd;
    }

    public void setCd(Set<CdDTO> cd) {
        this.cd = cd;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
