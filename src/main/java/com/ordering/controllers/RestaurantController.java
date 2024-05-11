package com.ordering.controllers;

import com.ordering.documents.Restaurant;
import com.ordering.dto.MenuDto;
import com.ordering.dto.RestaurantDto;
import com.ordering.services.RestaurantService;
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
@RequestMapping("/api/restaurant")
public class RestaurantController {
    ErrorValidationComponent errorValidationComponent;
    RestaurantService restaurantService;

    @Autowired
    RestaurantController(RestaurantService restaurantService, ErrorValidationComponent errorValidationComponent) {
        this.restaurantService = restaurantService;
        this.errorValidationComponent = errorValidationComponent;
    }

    @GetMapping
    public ResponseEntity<?> getAllRestaurant() {
        return new ResponseEntity<>(restaurantService.getAllRestaurant(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getRestaurantByName(@PathVariable String name) {
        log.info("Restaurant Search by Name: " + name);
        return new ResponseEntity<>(restaurantService.getRestaurantByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRestaurant(@Valid @RequestBody RestaurantDto restaurantDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Creating Restaurant: " + restaurantDto.getName());
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateRestaurant(@Valid @RequestBody RestaurantDto restaurantDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return errorValidationComponent.validationErrors(bindingResult);
        log.info("Updating restaurant: " + restaurantDto.getName());
        return new ResponseEntity<>(restaurantService.updateRestaurant(restaurantDto), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable String name) {
        restaurantService.deleteRestaurantByName(name);
        return new ResponseEntity<>("Deleting restaurant with name: " + name, HttpStatus.OK);
    }

}
