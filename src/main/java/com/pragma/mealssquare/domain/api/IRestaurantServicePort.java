package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;

public interface IRestaurantServicePort {
    void saveRestaurants (Restaurant restaurant);

    User getUserByEmail(String email);
    User getUserById(Long id);

    Restaurant getRestaurantByNit(String nitRestaurant);
}
