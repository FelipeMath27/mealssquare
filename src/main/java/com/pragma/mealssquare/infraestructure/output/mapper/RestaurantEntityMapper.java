package com.pragma.mealssquare.infraestructure.output.mapper;

import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.infraestructure.output.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {
    RestaurantEntity toRestaurantEntity(Restaurant restaurant);

    List<RestaurantEntity> tRestaurantEntityList(List<Restaurant> restaurantList);

    List<Restaurant> toRestaurantList(List<RestaurantEntity> restaurantEntityList);

    Restaurant toRestaurant(RestaurantEntity restaurantEntity);
}
