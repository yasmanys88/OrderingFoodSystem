package com.ordering.services.servicesImpl;

import com.ordering.documents.Restaurant;
import com.ordering.dto.RestaurantDto;
import com.ordering.exception.NotFoundException;
import com.ordering.repositories.RestaurantRepo;
import com.ordering.services.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    RestaurantRepo restaurantRepo;
    ModelMapper modelMapper;

    @Autowired
    RestaurantServiceImpl(RestaurantRepo restaurantRepo, ModelMapper modelMapper) {
        this.restaurantRepo = restaurantRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RestaurantDto> getAllRestaurant() {
        try {
            List<Restaurant> restaurantList = restaurantRepo.findAll();
            log.info("Converting Restaurant  List to DTO format");
            return restaurantList.stream()
                    .map(r -> modelMapper.map(r, RestaurantDto.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RestaurantDto getRestaurantByName(String name) {
        if (!restaurantRepo.existsByName(name)) throw new NotFoundException("Restaurant not found with name: " + name);
        try {
            return modelMapper.map(restaurantRepo.findByName(name),RestaurantDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        try {
            log.info("Converting RestaurantDto to Restaurant format and creating Restaurant");
            return modelMapper.map(restaurantRepo.save(modelMapper.map(restaurantDto, Restaurant.class)), RestaurantDto.class);
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
            return modelMapper.map(restaurantRepo.save(restaurant), RestaurantDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRestaurantByName(String name) {
        if (!restaurantRepo.existsByName(name)) throw new NotFoundException("Restaurant not found with Name: " + name);
        try {
            restaurantRepo.deleteByName(name);
            log.info("Restaurant with name: " + name + " was deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
