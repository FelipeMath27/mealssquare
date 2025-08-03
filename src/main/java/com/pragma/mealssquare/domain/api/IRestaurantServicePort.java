package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;

public interface IRestaurantServicePort {
    void saveRestaurants (Restaurant restaurant);

    Restaurant getRestaurantByNit(String nitRestaurant);

    Restaurant getRestaurantById(Long idRestaurant);

    User getUserByEmail(String email);

    User getUserById(Long id);
}
