package com.example.demo.controller;

import com.example.demo.models.Restaurant;
import com.example.demo.models.dto.RestaurantDto;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody RestaurantDto restaurant) {
        return ResponseEntity.ok(restaurantService.addRestaurant(restaurant));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Restaurant> editRestaurant(@RequestBody RestaurantDto restaurant, @PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.editRestaurant(restaurant, id));
    }


    @DeleteMapping("/{id}")
    public void deleteRestaurantById(@PathVariable Long id) {
        restaurantService.deleteRestaurantById(id);
    }
}
