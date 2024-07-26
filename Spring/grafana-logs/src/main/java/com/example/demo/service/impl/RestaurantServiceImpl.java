package com.example.demo.service.impl;

import com.example.demo.mappers.RestaurantMapper;
import com.example.demo.models.Restaurant;
import com.example.demo.models.dto.RestaurantDto;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.service.RestaurantService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }


    @Override
    public Restaurant addRestaurant(RestaurantDto restaurantDto) {
        return restaurantRepository.save(restaurantMapper.toEntity(restaurantDto));
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant editRestaurant(RestaurantDto restaurantDto, Long id) {
        return restaurantRepository.save(restaurantMapper.toEntity(restaurantDto));
    }

    @Override
    public void deleteRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }
}
