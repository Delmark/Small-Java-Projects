package com.example.demo.controller;

import com.example.demo.models.Food;
import com.example.demo.models.dto.FoodDto;
import com.example.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.getFoodById(id));
    }

    @GetMapping
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @PostMapping
    public ResponseEntity<Food> addFood(@RequestBody FoodDto food) {
        return ResponseEntity.ok(foodService.addFood(food));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Food> editFood(@RequestBody FoodDto food, @PathVariable Long id) {
        return ResponseEntity.ok(foodService.editFood(food, id));
    }

    @DeleteMapping("/{id}")
    public void deleteFoodById(@PathVariable Long id) {
        foodService.deleteFoodById(id);
    }
}
