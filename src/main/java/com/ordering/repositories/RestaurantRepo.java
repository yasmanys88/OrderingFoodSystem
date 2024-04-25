package com.ordering.repositories;

import com.ordering.documents.Restaurant;
import com.ordering.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepo extends MongoRepository<Restaurant, String> {
}
