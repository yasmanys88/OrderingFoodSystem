package com.ordering.controllers;

import com.ordering.dto.OrderDto;
import com.ordering.dto.OrderDtoId;
import com.ordering.services.OrderService;
import com.ordering.validations.ErrorValidationComponent;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {

    OrderService orderService;
    ErrorValidationComponent errorValidationComponent;

    @Autowired
    OrderController(OrderService orderService, ErrorValidationComponent errorValidationComponent) {
        this.orderService = orderService;
        this.errorValidationComponent = errorValidationComponent;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{number}")
    public ResponseEntity<?> getOrderByNumber(@PathVariable String number) {
        log.info("Menu Search by Name: " + number);
        return new ResponseEntity<>(orderService.getOrderByNumber(number), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Creating Order: " + orderDto.getOrderNumber());
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody OrderDtoId orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Updating order: " + orderDto.getOrderNumber());
        return new ResponseEntity<>(orderService.updateOrder(orderDto), HttpStatus.OK);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<?> deleteOrder(@PathVariable String number) {
        orderService.deleteOrderByNumber(number);
        return new ResponseEntity<>("Deleting order with number: " + number, HttpStatus.OK);
    }
}
