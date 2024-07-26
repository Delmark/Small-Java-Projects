package com.example.demo.mappers;

import com.example.demo.models.Menu;
import com.example.demo.models.dto.MenuDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMenuFromDto(MenuDto menuDto, @MappingTarget Menu menu);

    Menu toEntity(MenuDto menuDto);
}
