package com.example.demo.service;

import com.example.demo.models.Restaurant;
import com.example.demo.models.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurant(RestaurantDto restaurantDto);
    Restaurant getRestaurantById(Long id);
    List<Restaurant> getAllRestaurants();
    Restaurant editRestaurant(RestaurantDto restaurantDto, Long id);
    void deleteRestaurantById(Long id);
}
