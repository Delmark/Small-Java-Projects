package com.example.demo.repository;

import com.example.demo.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByRestaurantIdAndFoodId(Long restaurantId, Long foodId);
    List<Menu> findAllByRestaurantId(Long restaurantId);
    void deleteByRestaurantIdAndFoodId(Long restaurantId, Long foodId);
}
