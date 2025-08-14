package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Dish;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDishPersistencePort {
    void save(Dish dish);

    Optional<Dish> findById(Long idDish);

    List<Dish> findAllByRestaurantIdAndCategoryId(Long idRestaurant, Long idCategory, Pageable pageable);
}
