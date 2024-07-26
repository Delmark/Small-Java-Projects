package com.example.demo.controller;

import com.example.demo.models.Menu;
import com.example.demo.models.dto.MenuDto;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Menu>> getMenuById(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(menuService.getRestaurantMenu(restaurantId));
    }

    @PostMapping
    public ResponseEntity<Menu> addFoodToRestaurant(@RequestBody MenuDto menu) {
        return ResponseEntity.ok(menuService.addFoodToRestaurant(menu));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Menu> editFoodPrice(@RequestParam Double price, @RequestParam Long restaurantId, @PathVariable Long id) {
        return ResponseEntity.ok(menuService.editFoodPrice(id, restaurantId, price));
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<Void> removeFoodFromRestaurantMenu(@PathVariable Long restaurantId, @RequestParam Long foodId) {
        menuService.removeFoodFromRestaurantMenu(restaurantId, foodId);
        return ResponseEntity.ok().build();
    }
}
