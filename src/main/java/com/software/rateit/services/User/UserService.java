package com.software.rateit.services.User;

import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.Comments.CommentAlbumDTO;
import com.software.rateit.DTO.Comments.CommentsDTO;
import com.software.rateit.DTO.User.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {
    ResponseEntity<UserDTO> getOneById(long id);
    ResponseEntity<UserResponseWrapper> listOfUsers(Pageable pageable);
    ResponseEntity<UserDTO> findByEmail(String email);
    ResponseEntity<UserDTO> findByNick(String nick);
    ResponseEntity<UserDTO> registerNewUser(RegistrationDTO registrationDTO);
    ResponseEntity<UserDTO> changePassword(long id, PasswordChangeDTO passwordChangeDTO);
    ResponseEntity<UserDTO> addCdToUser(long userId, long cdId);
    ResponseEntity<Iterable<CdDTO>> getUsersCds(long id);
    ResponseEntity<UserDTO> login(LoginDTO loginDTO);
    ResponseEntity<Void> deleteUser(long id);
    ResponseEntity<CommentsDTO> commentAlbum(CommentAlbumDTO comment, long id);
    ResponseEntity<UserDTO> setAsAdmin(long id);
    ResponseEntity<Void> logout(long id);
    ResponseEntity<UserDTO> uploadAvatar(MultipartFile multipartFile, long id);
}
