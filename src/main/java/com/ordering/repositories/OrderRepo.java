package com.ordering.repositories;

import com.ordering.documents.Menu;
import com.ordering.documents.Order;
import com.ordering.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepo extends MongoRepository<Order, String> {
    Order findByOrderNumber(String number);

    boolean existsByOrderNumber(String number);

    void deleteByOrderNumber(String number);
}
