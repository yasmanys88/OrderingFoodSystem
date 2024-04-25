package com.ordering.repositories;

import com.ordering.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
}
