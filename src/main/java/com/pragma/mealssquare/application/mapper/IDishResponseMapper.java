package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.DishDTOResponse;
import com.pragma.mealssquare.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {ICategoryRequestMapper.class, RestaurantRequestMapper.class})
public interface IDishResponseMapper {
    DishDTOResponse toResponse(Dish dish);
}
