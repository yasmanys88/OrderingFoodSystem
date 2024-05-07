package com.ordering.repositories;

import com.ordering.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepo extends MongoRepository<User, String> {
    User findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

}
