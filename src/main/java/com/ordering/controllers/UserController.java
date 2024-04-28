package com.ordering.controllers;

import com.ordering.documents.User;
import com.ordering.dto.UserDto;
import com.ordering.services.UserService;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        log.info("Search for all Users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        log.info("User Search by Email: "+ email);
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        log.info("Creating user: "+ user.getName());
        return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
    }
    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        log.info("Updating user: "+ user.getName());
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        log.info("Deleting user with email: "+ email);
        return new ResponseEntity<>(userService.deleteUserByEmail(email),HttpStatus.OK);
    }

}
