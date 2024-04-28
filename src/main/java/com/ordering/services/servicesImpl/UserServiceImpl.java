package com.ordering.services.servicesImpl;

import com.ordering.documents.User;
import com.ordering.dto.UserDto;
import com.ordering.repositories.UserRepo;
import com.ordering.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    @Autowired
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo=userRepo;

    }

    @Override
    public List<?> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public UserDto createUser(User user) {
        return this.convertToDTO(userRepo.save(user));
    }
    @Override
    public String deleteUserById(String id) {
        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
        }
        userRepo.deleteById(id);
        return "User with id: " + id +" was deleted";
    }
    @Override
    public UserDto updateUser(User updatedUser) {

        if (!userRepo.existsById(updatedUser.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + updatedUser.getId());
        }
        updatedUser.setId(updatedUser.getId());
        return this.convertToDTO(userRepo.save(updatedUser));
    }
    public UserDto getUserById(String id) {
        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
        }
        return this.convertToDTO(userRepo.findById(id).get());
    }
    private UserDto convertToDTO(User user) {
        ModelMapper mapper= new ModelMapper();
     return mapper.map(user,UserDto.class);
    }

}
