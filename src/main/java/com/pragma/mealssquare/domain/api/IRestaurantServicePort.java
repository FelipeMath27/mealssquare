package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.PageResult;
import com.pragma.mealssquare.domain.model.Pagination;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;

import java.util.List;

public interface IRestaurantServicePort {
    void saveRestaurants (Restaurant restaurant, User user);

    Restaurant getRestaurantByNit(String nitRestaurant);

    Restaurant getRestaurantById(Long idRestaurant);

    PageResult<Restaurant> getAllRestaurants(Pagination pagination);
}
