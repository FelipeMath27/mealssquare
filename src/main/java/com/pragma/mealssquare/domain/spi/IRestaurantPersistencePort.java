package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;

import java.util.Optional;

public interface IRestaurantPersistencePort {
    void save(Restaurant restaurant);

    Optional<Restaurant> findRestaurantByNit(String nitRestaurant);

    Optional<Restaurant> findRestaurantById(Long idRestaurant);

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long id);
}
