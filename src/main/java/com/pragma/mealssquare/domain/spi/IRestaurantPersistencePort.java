package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);

    Restaurant getRestaurantByNit(String nitRestaurant);

    Restaurant getRestaurantById(Long idRestaurant);

    User getUserByEmail(String email);
    User getUserById(Long id);
}
