package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.DishDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.domain.model.Category;
import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {

    @Mapping(target = "category", source = "idCategory", qualifiedByName = "mapCategory")
    @Mapping(target = "restaurant", source = "idRestaurant", qualifiedByName = "mapRestaurant")
    Dish toDish(DishDTORequest dishDTORequest);

    @Named("mapCategory")
    default Category mapCategory(Long idCategory) {
        if (idCategory == null) {
            return null;
        }
        Category category = new Category();
        category.setIdCategory(idCategory);
        return category;
    }

    @Named("mapRestaurant")
    default Restaurant mapRestaurant(Long idRestaurant) {
        if (idRestaurant == null){
            return null;
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setIdRestaurant(idRestaurant);
        return restaurant;
    }
}
