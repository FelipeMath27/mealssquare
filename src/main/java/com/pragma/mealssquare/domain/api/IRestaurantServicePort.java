package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;

public interface IRestaurantServicePort {
    void saveRestaurants (Restaurant restaurant, String emailCreator);
}
