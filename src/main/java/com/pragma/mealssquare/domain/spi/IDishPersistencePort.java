package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Dish;

public interface IDishPersistencePort {
    void saveDish(Dish dish);

    Dish getDishById(Long idDish);
}
