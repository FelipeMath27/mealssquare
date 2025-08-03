package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.User;

public interface IDishServicePort {
    void saveDish(Dish dish);

    Dish getDishById(Long id);

    void updateDish(Dish dish, String email);

    void updateDishStatus(Dish dish, User user);
}
