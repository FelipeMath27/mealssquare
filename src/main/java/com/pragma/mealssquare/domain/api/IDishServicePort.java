package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.PageResult;
import com.pragma.mealssquare.domain.model.Pagination;
import com.pragma.mealssquare.domain.model.User;

import java.util.List;

public interface IDishServicePort {
    void saveDish(Dish dish);

    Dish getDishById(Long id);

    void updateDish(User user,Dish dish);

    void updateDishStatus(User user,Dish dish);

    PageResult<Dish> getDishList(Long idRestaurant, Long idCategory, Pagination pagination);
}
