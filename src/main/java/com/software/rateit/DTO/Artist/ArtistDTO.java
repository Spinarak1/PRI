package com.software.rateit.DTO.Artist;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.Track.TrackDTO;
import com.software.rateit.DTO.View;

import java.util.Set;

public class ArtistDTO {

    @JsonView(View.Summary.class)
    private long id;
    @JsonView(View.Summary.class)
    private String stageName;
    private Set<TrackDTO> tracks;
    private Set<CdDTO> cd;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Set<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(Set<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public Set<CdDTO> getCd() {
        return cd;
    }

    public void setCd(Set<CdDTO> cd) {
        this.cd = cd;
    }
}
