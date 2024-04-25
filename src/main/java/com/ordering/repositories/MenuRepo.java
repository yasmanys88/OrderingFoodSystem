package com.ordering.repositories;

import com.ordering.documents.Menu;
import com.ordering.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuRepo extends MongoRepository<Menu, String> {
}
