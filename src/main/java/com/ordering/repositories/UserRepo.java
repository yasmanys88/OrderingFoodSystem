package com.ordering.repositories;

import com.ordering.documents.User;
import com.ordering.dto.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepo extends MongoRepository<User, String> {
    User findByName(String name);
}
