package com.example.demo.repository;

import com.example.demo.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
