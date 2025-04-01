package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.domain.model.Restaurant;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestMapper {
    Restaurant toRestaurant(RestaurantDTORequest restaurantDTORequest);

    List<Restaurant> toRestaurantList(List<RestaurantDTORequest> restaurantDTORequestList);
}
