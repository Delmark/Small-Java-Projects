package com.example.demo.service.impl;

import com.example.demo.mappers.FoodMapper;
import com.example.demo.models.Food;
import com.example.demo.models.dto.FoodDto;
import com.example.demo.repository.FoodRepository;
import com.example.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }

    @Override
    public Food addFood(FoodDto food) {
        Food newFood = new Food();
        newFood.setName(food.getName());
        newFood.setDescription(food.getDescription());
        return foodRepository.save(newFood);
    }

    @Override
    public Food getFoodById(Long id) {
        return foodRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Food editFood(FoodDto food, Long id) {
        Food existingFood = foodRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        foodMapper.updateFoodFromDto(food, existingFood);
        return foodRepository.save(existingFood);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public void deleteFoodById(Long id) {
        foodRepository.deleteById(id);
    }
}
