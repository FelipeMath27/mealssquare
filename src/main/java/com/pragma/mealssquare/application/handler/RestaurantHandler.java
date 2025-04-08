package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;
import com.pragma.mealssquare.application.mapper.RestaurantRequestMapper;
import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.Rol;
import com.pragma.mealssquare.domain.model.User;

import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import com.pragma.mealssquare.infraestructure.exceptions.MicroserviceConnectionException;
import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        Rol creatorRol, ownerRol;
        try {
            User userCreatorRestaurant = usersMealsSquare.getUserByEmail(emailCreator);
            User userOwnerRestaurant = usersMealsSquare.getUserById(restaurantDTORequest.getIdOwner());
            Restaurant newRestaurant = restaurantRequestMapper.toRestaurant(restaurantDTORequest);
            iRestaurantServicePort.saveRestaurants(newRestaurant,userCreatorRestaurant,userOwnerRestaurant);
        } catch (FeignException.ServiceUnavailable ex){
            throw new MicroserviceConnectionException("Can't connect to the microservices User {}", ex);
        }
    }

    @Override
    public List<RestaurantDTOResponse> getListRestaurantDtoResponses(List<Restaurant> restaurantList) {
        return List.of();
    }
}
