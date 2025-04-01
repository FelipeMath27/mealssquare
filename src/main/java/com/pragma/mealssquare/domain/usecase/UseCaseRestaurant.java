package com.pragma.mealssquare.domain.usecase;


import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.validator.ValidatorClasses;
import com.pragma.mealssquare.infraestructure.exceptions.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;

import java.util.List;

public class UseCaseRestaurant implements IRestaurantServicePort {

    private final IRestaurantPersistencePort iRestaurantPersistencePort;

    public UseCaseRestaurant(IRestaurantPersistencePort iRestaurantPersistencePort) {
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
    }

    @Override
    public void saveRestaurants(List<Restaurant> restaurantList, String emailCreatorRestaurant) {
        if(restaurantList == null || restaurantList.isEmpty()){
            throw new CustomException(ConstantsErrorMessage.CANT_BE_EMPTY_LIST);
        }
        if(emailCreatorRestaurant.isEmpty()){
            throw new CustomException(ConstantsErrorMessage.CANT_BE_NULL);
        }
        for (Restaurant restaurant: restaurantList){
            if (!ValidatorClasses.isNumeric(restaurant.getNit()) ||
                    !ValidatorClasses.isValidPhone(restaurant.getPhoneNumberRestaurant()) ||
                    !ValidatorClasses.isValidNameRestaurant(restaurant.getNameRestaurant())){
                throw new CustomException(ConstantsErrorMessage.INVALID_DATA_FORMAT);
            }
            if (!ValidatorClasses.isNotEmpty(restaurant.getAddressRestaurant()) ||
                    !ValidatorClasses.isNotEmpty(restaurant.getUrlLogo()) ||
                    restaurant.getIdOwner() == null){
                throw new CustomException(ConstantsErrorMessage.CANT_BE_NULL);
            }

        }

    }
}
