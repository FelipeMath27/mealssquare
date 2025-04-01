package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
}
