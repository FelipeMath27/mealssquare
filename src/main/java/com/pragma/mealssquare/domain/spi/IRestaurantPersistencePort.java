package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
}
