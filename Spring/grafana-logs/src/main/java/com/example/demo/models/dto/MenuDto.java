package com.example.demo.models.dto;

import com.example.demo.models.Food;
import com.example.demo.models.Restaurant;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.example.demo.models.Menu}
 */
public class MenuDto implements Serializable {
    private final Long restaurantId;
    private final Long foodId;
    private final Double price;

    public MenuDto(Long restaurantId, Long foodId, Double price) {
        this.restaurantId = restaurantId;
        this.foodId = foodId;
        this.price = price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public Double getPrice() {
        return price;
    }
}