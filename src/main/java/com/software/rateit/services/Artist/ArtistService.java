package com.software.rateit.services.Artist;

import com.querydsl.core.types.Predicate;
import com.software.rateit.DTO.Artist.ArtistDTO;
import com.software.rateit.DTO.Artist.ArtistWrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ArtistService {

    ResponseEntity<ArtistWrapper> getAllArtists(Predicate predicate, Pageable pageable);
    ResponseEntity<ArtistDTO> addNewArtist(ArtistDTO artist);
    ResponseEntity<ArtistDTO> updateArtist(ArtistDTO artist, long id);
    ResponseEntity<ArtistDTO> getOneArtist(long id);
    ResponseEntity<Void> deleteArtist(long id);
    ResponseEntity<ArtistDTO> addCDToArtist(long id, long cdId);

}
