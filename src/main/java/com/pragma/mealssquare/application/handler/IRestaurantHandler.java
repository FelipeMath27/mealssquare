package com.pragma.mealssquare.application.handler;


import com.pragma.mealssquare.application.dto.PageDTOResponse;
import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;

public interface IRestaurantHandler {
    void saveListRestaurant(RestaurantDTORequest restaurantDTORequest);

    PageDTOResponse<RestaurantDTOResponse> getAllRestaurants(int page, int size);
}
