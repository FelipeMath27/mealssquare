package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;


public interface IRestaurantServicePort {
    void saveRestaurants (Restaurant restaurant, User user);

    Restaurant getRestaurantByNit(String nitRestaurant);

    Restaurant getRestaurantById(Long idRestaurant);

    PageResult<Restaurant> getAllRestaurants(Pagination pagination);
}
