package com.ordering.repositories;

import com.ordering.documents.Order;
import com.ordering.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, String> {
}
