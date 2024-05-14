package com.ordering.services.servicesImpl;

import com.ordering.documents.Order;
import com.ordering.dto.OrderDto;
import com.ordering.dto.OrderDtoId;
import com.ordering.exception.NotFoundException;
import com.ordering.repositories.MenuRepo;
import com.ordering.repositories.OrderRepo;
import com.ordering.repositories.RestaurantRepo;
import com.ordering.repositories.UserRepo;
import com.ordering.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    OrderRepo orderRepo;
    RestaurantRepo restaurantRepo;
    MenuRepo menuRepo;
    UserRepo userRepo;
    ModelMapper modelMapper;

    @Autowired
    OrderServiceImpl(OrderRepo orderRepo, RestaurantRepo restaurantRepo, MenuRepo menuRepo, UserRepo userRepo, ModelMapper modelMapper) {
        this.orderRepo = orderRepo;
        this.restaurantRepo = restaurantRepo;
        this.menuRepo = menuRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<?> getAllOrders() {
        try {
            List<Order> orderList = orderRepo.findAll();
            log.info("Converting Order  List to DTO format");
            return orderList.stream().map(o -> modelMapper.map(o, OrderDto.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDto getOrderByNumber(String number) {
        if (!orderRepo.existsByOrderNumber(number))
            throw new NotFoundException("Order not found with number: " + number);
        try {
            return modelMapper.map(orderRepo.findByOrderNumber(number), OrderDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        OrderExceptionMethod(orderDto);
        try {
            log.info("Converting OrderDto to Order format and creating Order");
            return modelMapper.map(orderRepo.save(modelMapper.map(orderDto, Order.class)), OrderDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDto updateOrder(OrderDtoId orderDto) {
        //variablee find by Optional
        Order order= orderRepo.findByOrderNumber(orderDto.getOrderNumber());
        if (order==null)
            throw new NotFoundException("Order not found with number: " + orderDto.getOrderNumber());
        try {
            orderDto.setId(order.getId());
            //Hacerlo como un Optional java8
            return modelMapper.map(orderRepo.save(modelMapper.map(orderDto, Order.class)), OrderDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void OrderExceptionMethod(OrderDto orderDto) {
        if (!userRepo.existsByEmail(orderDto.getUserEmail()))
            throw new NotFoundException("User not found with email: " + orderDto.getUserEmail());
        if (!restaurantRepo.existsByName(orderDto.getRestaurantName()))
            throw new NotFoundException("Restaurant not found with Name: " + orderDto.getRestaurantName());
        if (!menuRepo.existsByName(orderDto.getMenuName()))
            throw new NotFoundException("Menu not found with Name: " + orderDto.getMenuName());
    }

    @Override
    public void deleteOrderByNumber(String number) {
        if (!orderRepo.existsByOrderNumber(number))
            throw new NotFoundException("Restaurant not found with Name: " + number);
        try {
            orderRepo.deleteByOrderNumber(number);
            log.info("Order with number: " + number + " was deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
