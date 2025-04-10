package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;
import com.pragma.mealssquare.application.mapper.RestaurantRequestMapper;
import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.model.Restaurant;

import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RestaurantHandler implements IRestaurantHandler{

    private final IRestaurantServicePort iRestaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final IUsersMealsSquare usersMealsSquare;

    @Override
    public void saveListRestaurant(RestaurantDTORequest restaurantDTORequest, String emailCreator) {
            Restaurant newRestaurant = restaurantRequestMapper.toRestaurant(restaurantDTORequest);
            iRestaurantServicePort.saveRestaurants(newRestaurant,emailCreator);
    }

    @Override
    public List<RestaurantDTOResponse> getListRestaurantDtoResponses(List<Restaurant> restaurantList) {
        return List.of();
    }
}
