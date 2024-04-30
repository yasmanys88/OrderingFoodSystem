package com.ordering.repositories;

import com.ordering.documents.Restaurant;
import com.ordering.documents.User;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepo extends MongoRepository<Restaurant, String> {
    Restaurant findByName(String name);

    boolean existsByName(String name);

    void deleteByName(String name);


}
