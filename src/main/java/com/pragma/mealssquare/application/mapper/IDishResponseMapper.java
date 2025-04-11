package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.DishDTOResponse;
import com.pragma.mealssquare.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {
    DishDTOResponse toResponse(Restaurant restaurant);
}
