package com.example.demo.mappers;

import com.example.demo.models.Restaurant;
import com.example.demo.models.dto.RestaurantDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRestaurantFromDto(RestaurantDto restaurantDto, @MappingTarget Restaurant restaurant);

    Restaurant toEntity(RestaurantDto restaurantDto);
}
