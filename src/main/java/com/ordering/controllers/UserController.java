package com.ordering.controllers;

import com.ordering.dto.UserDto;
import com.ordering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(userService.getAllUsers());
    }

   /* @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        UserDto createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }*/

}
