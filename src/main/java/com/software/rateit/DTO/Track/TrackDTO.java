package com.software.rateit.DTO.Track;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.Artist.ArtistDTO;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.View;

import java.util.Set;
@JsonView({View.Summary.class, View.Comment.class})
public class TrackDTO {

    @JsonView({View.Summary.class, View.Comment.class})
    private long id;
    @JsonView({View.Summary.class, View.Comment.class})
    private String title;
    @JsonView({View.Summary.class, View.Comment.class})
    private Integer releaseDate;
    @JsonView({View.Summary.class, View.Comment.class})
    private String artist;
    private Set<CdDTO> cd;
    @JsonView({View.Summary.class, View.Comment.class})
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
