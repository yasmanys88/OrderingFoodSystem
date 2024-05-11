package com.ordering.services.servicesImpl;

import com.ordering.documents.User;
import com.ordering.dto.UserDto;
import com.ordering.exception.NotFoundException;
import com.ordering.repositories.UserRepo;
import com.ordering.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    ModelMapper modelMapper;

    @Autowired
    UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<?> getAllUsers() {
        try {
            List<User> users = userRepo.findAll();
            log.info("Converting users to DTO format");
            return users.stream()
                    .map(u -> modelMapper.map(u, UserDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        try {
            log.info("Converting UserDto to User format and creating user");
            return modelMapper.map(userRepo.save(modelMapper.map(userDto, User.class)), UserDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserByEmail(String email) {
        if (!userRepo.existsByEmail(email)) throw new NotFoundException("User not found with email " + email);
        try {
            log.info("Deleting user with email: " + email);
            userRepo.deleteByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto updateUser(UserDto updatedUser) {
        if (!userRepo.existsByEmail(updatedUser.getEmail()))
            throw new NotFoundException("User not found with email " + updatedUser.getEmail());
        try {
            User userDb = userRepo.findByEmail(updatedUser.getEmail());
            log.info("Updating user fields");
            userDb.setDeliveryAddress(updatedUser.getDeliveryAddress());
            userDb.setEmail(updatedUser.getEmail());
            userDb.setName(updatedUser.getName());
            return modelMapper.map(userRepo.save(userDb), UserDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDto getUserByEmail(String email) {
        if (!userRepo.existsByEmail(email)) throw new NotFoundException("User not found with email " + email);
        try {
            log.info("Looking for user information with email: " + email);
            return modelMapper.map(userRepo.findByEmail(email), UserDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
