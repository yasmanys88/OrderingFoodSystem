package com.ordering.controllers;

import com.ordering.documents.User;
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
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String name) {
        return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
    }
    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String name) {
        return new ResponseEntity<>(userService.deleteUserByName(name),HttpStatus.OK);
    }

}
