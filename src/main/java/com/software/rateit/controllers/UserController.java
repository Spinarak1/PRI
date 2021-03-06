package com.software.rateit.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.CD.CdDTO;
import com.software.rateit.DTO.Rate.RateDTO;
import com.software.rateit.DTO.User.*;
import com.software.rateit.DTO.View;
import com.software.rateit.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {


    @Autowired
    private UserService service;

    @JsonView(View.Summary.class)
    @GetMapping("/users")
    public ResponseEntity<UserResponseWrapper> getAllUsers(@PageableDefault(value = 10, page = 0) Pageable pageable) {
        return service.listOfUsers(pageable);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable Long id) {
        return service.getOneById(id);
    }

    @GetMapping("/user-by-nick")
    public ResponseEntity<UserDTO> findByUsername(@RequestParam String nick) {
        return service.findByNick(nick);
    }

    @GetMapping("/user-by-email")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }

    @GetMapping("/users/{id}/cds")
    public ResponseEntity<Iterable<CdDTO>> getUsersCd(@PathVariable long id){
        return service.getUsersCds(id);
    }

    @JsonView(View.Summary.class)
    @GetMapping("/users/{id}/rated")
    public ResponseEntity<List<RateDTO>> getRatedAlbums(@PathVariable long id){
        return service.getRatedAlbums(id);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> register(@RequestBody RegistrationDTO registrationDTO){
        return service.registerNewUser(registrationDTO);
    }

    @PostMapping("/users/{id}/change-password")
    public ResponseEntity<UserDTO> changePassword(@PathVariable long id,
                                                  @RequestBody PasswordChangeDTO passwordChangeDTO){
        return service.changePassword(id, passwordChangeDTO);
    }

    @PostMapping("/users/{id}/change-email")
    public ResponseEntity<UserDTO> changeEmail(@PathVariable long id,
                                                  @RequestBody ChangeEmailDTO changeEmailDTO){
        return service.changeEmail(changeEmailDTO, id);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO){
        return service.login(loginDTO);
    }

    @PostMapping("/users/{id}/logout")
    public ResponseEntity<Void> logout(@PathVariable long id){
        return service.logout(id);
    }

    @PostMapping("/users/{id}/add-cd/{cd-id}")
    public ResponseEntity<UserDTO> addCdToUser(@PathVariable long id, @PathVariable("cd-id") long cd){
        return service.addCdToUser(id, cd);
    }

    @PostMapping("/users/{id}/set-as-admin")
    public ResponseEntity<UserDTO> setAsAdmin(@PathVariable long id){
        return service.setAsAdmin(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        return service.deleteUser(id);
    }

    @PostMapping(value = "/users/{id}/set-avatar", headers = "content-type=multipart/*")
    public ResponseEntity<UserDTO> addAvatar(@RequestParam("file") MultipartFile file, @PathVariable long id){
        return service.uploadAvatar(file, id);
    }


}
