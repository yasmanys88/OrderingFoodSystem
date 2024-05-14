package com.ordering.services;

import com.ordering.dto.MenuDto;
import com.ordering.dto.OrderDto;
import com.ordering.dto.OrderDtoId;

import java.util.List;

public interface OrderService {

    List<?> getAllOrders();

    OrderDto createOrder(OrderDto orderDto);

    OrderDto updateOrder(OrderDtoId orderDto);

    void deleteOrderByNumber(String number);

    OrderDto getOrderByNumber(String number);
}
