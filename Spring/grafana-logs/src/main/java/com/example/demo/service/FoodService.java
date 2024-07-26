package com.example.demo.service;

import com.example.demo.models.Food;
import com.example.demo.models.dto.FoodDto;

import java.util.List;

public interface FoodService {
    Food addFood(FoodDto food);
    Food getFoodById(Long id);
    Food editFood(FoodDto food, Long id);
    List<Food> getAllFoods();
    void deleteFoodById(Long id);
}
