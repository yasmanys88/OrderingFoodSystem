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
import java.util.List;
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
    public UserDto createUser(UserDto user) {
        return this.convertToDTO(userRepo.save(this.convertToDocument(user)));
    }
    @Override
    public String deleteUserByEmail(String email) {
        if (!userRepo.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email);
        }
        userRepo.deleteByEmail(email);
        return "User with email: " + email +" was deleted";
    }
    @Override
    public UserDto updateUser(UserDto updatedUser) {

        if (!userRepo.existsByEmail(updatedUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + updatedUser.getEmail());
        }
        User userDb= userRepo.findByEmail(updatedUser.getEmail());
        userDb.setDeliveryAddress(updatedUser.getDeliveryAddress());
        userDb.setEmail(updatedUser.getEmail());
        userDb.setName(updatedUser.getName());
        return this.convertToDTO(userRepo.save(userDb));
    }
    @Override
    public UserDto getUserByEmail(String email) {
        if (!userRepo.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email);
        }
        return this.convertToDTO(userRepo.findByEmail(email));
    }
    private UserDto convertToDTO(User user) {
        ModelMapper mapper= new ModelMapper();
     return mapper.map(user,UserDto.class);
    }
    private User convertToDocument(UserDto user) {
        ModelMapper mapper= new ModelMapper();
        return mapper.map(user,User.class);
    }

}
