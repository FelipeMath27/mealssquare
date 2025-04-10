package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.User;

public interface IDishServicePort {
    void saveNewDish(Dish newDish, String emailOwner);
}
