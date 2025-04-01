package com.pragma.mealssquare.domain.usecase;


import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.Rol;
import com.pragma.mealssquare.domain.model.TypeRolEnum;
import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.validator.ValidatorClasses;
import com.pragma.mealssquare.infraestructure.exceptions.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;

import java.util.ArrayList;
import java.util.List;

public class UseCaseRestaurant implements IRestaurantServicePort {

    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IUsersMealsSquare usersMealsSquare;

    public UseCaseRestaurant(IRestaurantPersistencePort iRestaurantPersistencePort, IUsersMealsSquare usersMealsSquare) {
        this.iRestaurantPersistencePort = iRestaurantPersistencePort;
        this.usersMealsSquare = usersMealsSquare;
    }

    @Override
    public void saveRestaurants(List<Restaurant> restaurantList, String emailCreatorRestaurant) {
        if(restaurantList == null || restaurantList.isEmpty()){
            throw new CustomException(ConstantsErrorMessage.CANT_BE_EMPTY_LIST);
        }
        if(emailCreatorRestaurant.isEmpty()){
            throw new CustomException(ConstantsErrorMessage.CANT_BE_NULL);
        }
        User creatorUser = usersMealsSquare.getUserByEmail(emailCreatorRestaurant);
        if(creatorUser == null){
            throw new CustomException(ConstantsErrorMessage.ADMIN_NOT_FOUND);
        }
        List<Restaurant> realRestaurantList = new ArrayList<>();
        for (Restaurant restaurant: restaurantList){
            if (!ValidatorClasses.isNumeric(restaurant.getNit()) ||
                    !ValidatorClasses.isValidPhone(restaurant.getPhoneNumberRestaurant()) ||
                    !ValidatorClasses.isValidNameRestaurant(restaurant.getNameRestaurant())){
                throw new CustomException(ConstantsErrorMessage.INVALID_DATA_FORMAT);
            }
            if (!ValidatorClasses.isNotEmpty(restaurant.getAddressRestaurant()) ||
                    !ValidatorClasses.isNotEmpty(restaurant.getUrlLogo()) ||
                    restaurant.getIdOwnerUser() == null){
                throw new CustomException(ConstantsErrorMessage.CANT_BE_NULL);
            }
            User newOwnerUser = usersMealsSquare.getUserById(restaurant.getIdOwnerUser());
            if (newOwnerUser == null){
                throw new CustomException(ConstantsErrorMessage.ROL_NOT_FOUND);
            }
            Rol newRolRestaurant = usersMealsSquare.getRoleById(newOwnerUser.getRol().getIdRol());
            if (!TypeRolEnum.OWNER.name().equals(newRolRestaurant.getNameRol())){
                throw new CustomException(ConstantsErrorMessage.NOT_OWNER_ROL);
            }
            restaurant.setIdOwnerUser(newOwnerUser.getIdUser());
            realRestaurantList.add(restaurant);
        }
        for(Restaurant restaurant: realRestaurantList){
            iRestaurantPersistencePort.saveRestaurant(restaurant);
        }
    }
}
