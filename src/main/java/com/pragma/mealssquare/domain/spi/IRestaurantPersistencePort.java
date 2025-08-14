package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.domain.model.PageResult;
import com.pragma.mealssquare.domain.model.Pagination;
import com.pragma.mealssquare.domain.model.Restaurant;

import java.util.Optional;

public interface IRestaurantPersistencePort {
    void save(Restaurant restaurant);

    Optional<Restaurant> findRestaurantByNit(String nitRestaurant);

    Optional<Restaurant> findRestaurantById(Long idRestaurant);

    Optional<UserDTOResponse> findUserByEmail(String email);
    Optional<UserDTOResponse> findUserById(Long id);


    PageResult<Restaurant> getAllRestaurants(Pagination pagination);
}
