package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;
import com.pragma.mealssquare.application.mapper.RestaurantRequestMapper;
import com.pragma.mealssquare.application.mapper.RestaurantResponseMapper;
import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.validator.ValidatorClasses;
import com.pragma.mealssquare.infraestructure.exceptions.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RestaurantHandler implements IRestaurantHandler{

    private final IRestaurantServicePort iRestaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;

    public RestaurantHandler(IRestaurantServicePort iRestaurantServicePort, RestaurantRequestMapper restaurantRequestMapper) {
        this.iRestaurantServicePort = iRestaurantServicePort;
        this.restaurantRequestMapper = restaurantRequestMapper;
    }

    @Override
    public void saveListRestaurant(List<RestaurantDTORequest> restaurantDTORequestList, String emailCreator) {
        if(!ValidatorClasses.isNotEmpty(emailCreator)){
            throw new CustomException(ConstantsErrorMessage.CANT_BE_NULL);
        }
        if(restaurantDTORequestList == null || restaurantDTORequestList.isEmpty()){
            throw new CustomException(ConstantsErrorMessage.CANT_BE_EMPTY_LIST);
        }
        List<Restaurant> restaurantList =restaurantRequestMapper.toRestaurantList(restaurantDTORequestList);
        iRestaurantServicePort.saveRestaurants(restaurantList,emailCreator);
    }

    @Override
    public List<RestaurantDTOResponse> getListRestaurantDtoResponses(List<Restaurant> restaurantList) {
        return List.of();
    }
}
