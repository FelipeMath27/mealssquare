package com.pragma.mealssquare.application.handler;


import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;

import java.util.List;

public interface IRestaurantHandler {
    void saveListRestaurant(RestaurantDTORequest restaurantDTORequest);

    List<RestaurantDTOResponse> getListRestaurantDtoResponses();
}
