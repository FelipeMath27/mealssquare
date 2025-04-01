package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    void saveRestaurants (List<Restaurant> restaurantList, String emailCreatorRestaurant);
}
