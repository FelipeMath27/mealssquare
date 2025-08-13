package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantPersistencePort {
    void save(Restaurant restaurant);

    Optional<Restaurant> findRestaurantByNit(String nitRestaurant);

    Optional<Restaurant> findRestaurantById(Long idRestaurant);

    Optional<UserDTOResponse> findUserByEmail(String email);
    Optional<UserDTOResponse> findUserById(Long id);

    List<Restaurant> getAllRestaurants();
}
