package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.DishDTORequest;

import com.pragma.mealssquare.application.dto.DishDTOStatusRequest;
import com.pragma.mealssquare.application.dto.DishUpdateDTORequest;
import com.pragma.mealssquare.domain.model.Category;
import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {ICategoryRequestMapper.class, RestaurantRequestMapper.class})
public interface IDishRequestMapper {

    @Mapping(target = "category", expression = "java(createCategoryById(dishDTORequest.getIdCategory()))")
    @Mapping(target = "restaurant", expression = "java(createRestaurantById(dishDTORequest.getIdRestaurant()))")
    Dish toDish(DishDTORequest dishDTORequest);

    default Category createCategoryById(Long idCategory) {
        if (idCategory == null) {
            return null;
        }
        return new Category(idCategory, null, null); //
    }

    default Restaurant createRestaurantById(Long idRestaurant) {
        if (idRestaurant == null) {
            return null;
        }
        return new Restaurant(idRestaurant,null,null,null,null,null,null); //
    }

    Dish toDishUpdate(DishUpdateDTORequest dishUpdateDTORequest);

    Dish toDishStatusUpdate(DishDTOStatusRequest dishDTOStatusRequest);
}
