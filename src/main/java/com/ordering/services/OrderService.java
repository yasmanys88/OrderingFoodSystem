package com.ordering.services;

import com.ordering.dto.MenuDto;
import com.ordering.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<?> getAllOrders();

    OrderDto createOrder(OrderDto orderDto);

    OrderDto updateOrder(OrderDto orderDto);

    void deleteOrderByNumber(Integer number);

    OrderDto getOrderByNumber(Integer number);
}
