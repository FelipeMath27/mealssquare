package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;

import java.util.Optional;

public interface IDishPersistencePort {
    void save(Dish dish);

    Optional<Dish> findById(Long idDish);

    PageResult<Dish> findDishesByIdRestaurant(Long idRestaurant, Long idCategory, Pagination pagination);
}
