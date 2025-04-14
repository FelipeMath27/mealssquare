package com.pragma.mealssquare.infraestructure.output.mapper;

import com.pragma.mealssquare.domain.model.Category;
import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.infraestructure.output.entity.CategoryEntity;
import com.pragma.mealssquare.infraestructure.output.entity.DishEntity;
import com.pragma.mealssquare.infraestructure.output.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {

    @Mapping(source = "category.idCategory", target = "categoryEntity.idCategory")
    @Mapping(source = "restaurant.idRestaurant", target = "restaurantEntity.idRestaurant")
    DishEntity toDishEntity(Dish dish);

    @Mapping(source = "categoryEntity", target = "category")
    @Mapping(source = "restaurantEntity", target = "restaurant")
    Dish toDish(DishEntity dishEntity);

    default Category mapCategoryEntityToCategory(CategoryEntity categoryEntity){
        if (categoryEntity == null){
            return null;
        }
        return new Category(categoryEntity.getIdCategory(),categoryEntity.getNameCategory(),categoryEntity.getDescriptionCategory());
    }

    default Restaurant mapRestaurantEntityToRestaurant(RestaurantEntity restaurantEntity){
        if (restaurantEntity == null){
            return null;
        }
        return new Restaurant(restaurantEntity.getIdRestaurant(), restaurantEntity.getNameRestaurant(), restaurantEntity.getAddressRestaurant(),
                restaurantEntity.getIdOwner(), restaurantEntity.getPhoneNumberRestaurant(),restaurantEntity.getUrlLogo(),restaurantEntity.getNit());
    }
}
