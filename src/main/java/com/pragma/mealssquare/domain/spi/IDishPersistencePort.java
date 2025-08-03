package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Dish;

import java.util.Optional;

public interface IDishPersistencePort {
    void save(Dish dish);

    Optional<Dish> findById(Long idDish);
}
