package com.ordering.services;

import com.ordering.documents.User;
import com.ordering.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    List<?> getAllUsers();
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user);
    String deleteUserByName(String name);
    UserDto getUserByName(String name);

}
