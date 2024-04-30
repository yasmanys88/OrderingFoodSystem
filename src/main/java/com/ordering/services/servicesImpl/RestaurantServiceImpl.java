package com.ordering.services.servicesImpl;

import com.ordering.documents.Restaurant;
import com.ordering.dto.RestaurantDto;
import com.ordering.repositories.RestaurantRepo;
import com.ordering.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    RestaurantRepo restaurantRepo;

    @Autowired
    RestaurantServiceImpl(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    @Override
    public List<?> getAllRestaurant() {
        List<Restaurant> restaurantList = restaurantRepo.findAll();
        log.info("Converting Restaurant  List to DTO format");
        return restaurantList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public RestaurantDto getRestaurantByName(String name) {
        if (!restaurantRepo.existsByName(name))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with name: " + name);
        return this.convertToDTO(restaurantRepo.findByName(name));
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        log.info("Converting RestaurantDto to Restaurant format and creating Restaurant");
        return this.convertToDTO(restaurantRepo.save(this.convertToDocument(restaurantDto)));
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto) {
        if (!restaurantRepo.existsByName(restaurantDto.getName())) {
            log.error("Restaurant not found with Name: " + restaurantDto.getName());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with Name: " + restaurantDto.getName());
        }
        Restaurant restaurant = restaurantRepo.findByName(restaurantDto.getName());
        log.info("Updating Restaurant fields");
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setContact_info(restaurantDto.getContact_info());
        restaurant.setMenu_name(restaurantDto.getMenu_name());
        return this.convertToDTO(restaurantRepo.save(restaurant));
    }

    @Override
    public String deleteRestaurantByName(String name) {
        if (!restaurantRepo.existsByName(name)) {
            log.error("Restaurant not found with name: " + name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found with email: " + name);
        }
        restaurantRepo.deleteByName(name);
        log.info("Restaurant with name: " + name + " was deleted");
        return "Restaurant with name: " + name + " was deleted";
    }

    private RestaurantDto convertToDTO(Restaurant restaurant) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(restaurant, RestaurantDto.class);
    }

    private Restaurant convertToDocument(RestaurantDto restaurantDto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(restaurantDto, Restaurant.class);
    }

}
