package com.ordering.services.servicesImpl;

import com.ordering.dto.OrderDto;
import com.ordering.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<?> getAllOrders() {
        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public void deleteOrderByNumber(Integer number) {

    }

    @Override
    public OrderDto getOrderByNumber(Integer number) {
        return null;
    }
}
