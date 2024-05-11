package com.ordering.services.servicesImpl;

import com.ordering.documents.Restaurant;
import com.ordering.dto.RestaurantDto;
import com.ordering.exception.NotFoundException;
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
    public List<RestaurantDto> getAllRestaurant() {
        try {
            List<Restaurant> restaurantList = restaurantRepo.findAll();
            log.info("Converting Restaurant  List to DTO format");
            return restaurantList.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RestaurantDto getRestaurantByName(String name) {
        if (!restaurantRepo.existsByName(name)) throw new NotFoundException("Restaurant not found with name: " + name);
        try {
            return this.convertToDTO(restaurantRepo.findByName(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        try {
            log.info("Converting RestaurantDto to Restaurant format and creating Restaurant");
            return this.convertToDTO(restaurantRepo.save(this.convertToDocument(restaurantDto)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto) {
        if (!restaurantRepo.existsByName(restaurantDto.getName()))
            throw new NotFoundException("Restaurant not found with Name: " + restaurantDto.getName());

        try {
            Restaurant restaurant = restaurantRepo.findByName(restaurantDto.getName());
            log.info("Updating Restaurant fields");
            restaurant.setName(restaurantDto.getName());
            restaurant.setAddress(restaurantDto.getAddress());
            restaurant.setContact_info(restaurantDto.getContact_info());
            restaurant.setMenu(restaurantDto.getMenu());
            return this.convertToDTO(restaurantRepo.save(restaurant));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteRestaurantByName(String name) {
        if (!restaurantRepo.existsByName(name)) throw new NotFoundException("Restaurant not found with Name: " + name);
        try {
            restaurantRepo.deleteByName(name);
            log.info("Restaurant with name: " + name + " was deleted");
            return "Restaurant with name: " + name + " was deleted";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
