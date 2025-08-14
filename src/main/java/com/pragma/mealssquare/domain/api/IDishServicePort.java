package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.User;

import java.util.List;

public interface IDishServicePort {
    void saveDish(Dish dish);

    Dish getDishById(Long id);

    void updateDish(User user,Dish dish);

    void updateDishStatus(User user,Dish dish);

    List<Dish> getDishList(Long idRestaurant, int page, int size, Long idCategory);
}
