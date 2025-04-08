package com.pragma.mealssquare.application.handler;


import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;
import com.pragma.mealssquare.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantHandler {
    void saveListRestaurant(RestaurantDTORequest restaurantDTORequest, String emailCreator);

    List<RestaurantDTOResponse> getListRestaurantDtoResponses(List<Restaurant> restaurantList);
}
