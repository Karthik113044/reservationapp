package com.reservationapp.controller;

import com.reservationapp.entity.UserRegistration;
import com.reservationapp.payload.UserRegistrationDto;
import com.reservationapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
public class UserRegistrationController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestParam("name") String name,
                                               @RequestParam("email") String email,
                                               @RequestParam("password") String password,
                                               @RequestParam("profilePicture") MultipartFile profilePicture) {
        try {
            // Convert MultipartFile to byte[]
            byte[] pictureBytes = profilePicture.getBytes();

            // Create a new UserRegistration object
            UserRegistration userRegistration = new UserRegistration();
            userRegistration.setName(name);
            userRegistration.setEmail(email);
            userRegistration.setPassword(password);
            userRegistration.setProfilePicture(profilePicture.getBytes());

            // Save the user registration
            UserRegistrationDto userRegistrationDto = userServiceImpl.createUser(userRegistration);

            return new ResponseEntity<>( "User registered successfully" + userRegistrationDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to register user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserbyId(@PathVariable long id){

        UserRegistration userRegistrationDto = userServiceImpl.getUserbyId(id);
        return new ResponseEntity<>(userRegistrationDto,HttpStatus.OK);
    }
}