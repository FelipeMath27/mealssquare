package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;
import com.pragma.mealssquare.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantResponseMapper {
    RestaurantDTOResponse toRestaurantDtoResponse(Restaurant restaurant);

    List<RestaurantDTOResponse> toRestaurantDtoList(List<Restaurant> restaurant);
}
