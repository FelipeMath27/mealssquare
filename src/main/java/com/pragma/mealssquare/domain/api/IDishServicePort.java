package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Dish;

public interface IDishServicePort {
    void saveNewDish(Dish dish, String emailOwner);

    Dish getDishById(Long id);

    void updateDish(Dish dish, String emailOwner);
}
