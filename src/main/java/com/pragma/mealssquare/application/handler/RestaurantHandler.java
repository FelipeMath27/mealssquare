package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.application.mapper.RestaurantRequestMapper;
import com.pragma.mealssquare.application.mapper.RestaurantResponseMapper;
import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.model.Restaurant;

import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final IUserFeignHandler iUserFeignHandler;
    private final IUserResponseMapper iUserResponseMapper;
    private final RestaurantResponseMapper restaurantResponseMapper;


    @Override
    public void saveListRestaurant(RestaurantDTORequest restaurantDTORequest) {
        User userOwner;
        try {
            userOwner = iUserResponseMapper.toUser(iUserFeignHandler.getUserById(restaurantDTORequest.getIdOwner()));
        } catch (UsernameNotFoundException ex) {
            throw new InfrastructureException(ConstantsErrorMessage.USER_NOT_FOUD, ex);
        }

        Restaurant newRestaurant = restaurantRequestMapper.toRestaurant(restaurantDTORequest);
        iRestaurantServicePort.saveRestaurants(newRestaurant, userOwner);
    }

    @Override
    public List<RestaurantDTOResponse> getListRestaurantDtoResponses() {
        List<Restaurant> restaurantList = iRestaurantServicePort.getAllRestaurants();
        return restaurantResponseMapper.toRestaurantDtoList(restaurantList);
    }
}
