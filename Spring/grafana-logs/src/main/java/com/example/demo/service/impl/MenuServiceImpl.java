package com.example.demo.service.impl;

import com.example.demo.mappers.MenuMapper;
import com.example.demo.models.Food;
import com.example.demo.models.Menu;
import com.example.demo.models.Restaurant;
import com.example.demo.models.dto.MenuDto;
import com.example.demo.repository.MenuRepository;
import com.example.demo.service.FoodService;
import com.example.demo.service.MenuService;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final FoodService foodService;
    private final RestaurantService restaurantService;

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper, FoodService foodService, RestaurantService restaurantService) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.foodService = foodService;
        this.restaurantService = restaurantService;
    }

    @Override
    public Menu addFoodToRestaurant(MenuDto dto) {
        Food food = foodService.getFoodById(dto.getFoodId());
        Restaurant restaurant = restaurantService.getRestaurantById(dto.getRestaurantId());;
        Menu newMenu = new Menu(restaurant, food, dto.getPrice());
        return menuRepository.save(newMenu);
    }

    @Override
    public Menu editFoodPrice(Long foodId, Long restaurantId, double price) {
        Menu menu = menuRepository.findByRestaurantIdAndFoodId(restaurantId, foodId);
        menu.setPrice(price);
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getRestaurantMenu(Long restaurantId) {
        return menuRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public void removeFoodFromRestaurantMenu(Long restaurantId, Long foodId) {
        menuRepository.deleteByRestaurantIdAndFoodId(restaurantId, foodId);
    }
}
