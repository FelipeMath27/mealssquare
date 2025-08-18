package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.model.Restaurant;

import java.util.Optional;

public interface IRestaurantPersistencePort {
    void save(Restaurant restaurant);

    Optional<Restaurant> findRestaurantByNit(String nitRestaurant);

    Optional<Restaurant> findRestaurantById(Long idRestaurant);

    PageResult<Restaurant> getAllRestaurants(Pagination pagination);
}
