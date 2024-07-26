package com.example.demo.service;

import com.example.demo.models.Menu;
import com.example.demo.models.dto.MenuDto;
import java.util.List;

public interface MenuService {
    Menu addFoodToRestaurant(MenuDto menu);
    Menu editFoodPrice(Long foodId, Long restaurantId, double price);
    List<Menu> getRestaurantMenu(Long restaurantId);
    void removeFoodFromRestaurantMenu(Long restaurantId, Long foodId);
}
