package com.example.demo.mappers;

import com.example.demo.models.Food;
import com.example.demo.models.dto.FoodDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFoodFromDto(FoodDto foodDto, @MappingTarget Food food);
    Food toEntity(FoodDto foodDto);
}
