package com.ordering.services.servicesImpl;

import com.ordering.documents.User;
import com.ordering.dto.UserDto;
import com.ordering.repositories.UserRepo;
import com.ordering.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public UserDto convertToDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setDeliveryAddress(user.getDeliveryAddress());
        return userDTO;
    }


}
