package com.ordering.controllers;

import com.ordering.dto.UserDto;
import com.ordering.services.UserService;
import com.ordering.validations.ErrorValidationComponent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService userService;
    ErrorValidationComponent errorValidationComponent;


    @Autowired
    UserController(UserService userService, ErrorValidationComponent errorValidationComponent) {
        this.userService = userService;
        this.errorValidationComponent = errorValidationComponent;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        log.info("Search for all Users");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        log.info("User Search by Email: " + email);
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Creating user: " + user.getName());
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Updating user: " + user.getName());
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        userService.deleteUserByEmail(email);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
