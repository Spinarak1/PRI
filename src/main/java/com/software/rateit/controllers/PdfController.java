package com.software.rateit.controllers;

import com.software.rateit.services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class PdfController {

    @Autowired
    private UserService userService;

    @GetMapping("/generate-raport")
    public ResponseEntity<InputStreamResource> generateRaport(@RequestParam int days){
        return userService.generateRaport(days);
    }
}
