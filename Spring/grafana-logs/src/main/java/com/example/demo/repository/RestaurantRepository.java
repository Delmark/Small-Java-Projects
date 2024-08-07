package com.example.demo.repository;

import com.example.demo.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
