package com.ordering.services;

import com.ordering.dto.RestaurantDto;
import com.ordering.dto.UserDto;

import java.util.List;

public interface RestaurantService {

    List<?> getAllRestaurant();

    RestaurantDto getRestaurantByName(String name);

    RestaurantDto createRestaurant(RestaurantDto restaurantDto);

    RestaurantDto updateRestaurant(RestaurantDto restaurantDto);

    void deleteRestaurantByName(String name);


}
