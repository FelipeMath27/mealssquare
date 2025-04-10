package com.pragma.mealssquare.domain.usecase;


import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;


import com.pragma.mealssquare.domain.validator.ValidatorClasses;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@Slf4j
public class UseCaseRestaurant implements IRestaurantServicePort {

    private static final Logger logger = LoggerFactory.getLogger(UseCaseRestaurant.class);

    private final IRestaurantPersistencePort iRestaurantPersistencePort;

    @Override
    public void saveRestaurants(Restaurant restaurant, String emailCreator) {
        User userCreatorRestaurant = iRestaurantPersistencePort.getUserByEmail(emailCreator);
        User userOwnerRestaurant = iRestaurantPersistencePort.getUserById(restaurant.getIdOwner());
        ValidatorClasses.validateAdminCreator(userCreatorRestaurant);
        ValidatorClasses.validateOwner(userOwnerRestaurant);
        processValidateSaveRestaurant(restaurant);
    }

    private void processValidateSaveRestaurant(Restaurant restaurant){
        restaurant.setNameRestaurant(ValidatorClasses.validateRestaurantName(restaurant.getNameRestaurant())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.INVALID_RESTAURANT_NAME_FORMAT)));
        restaurant.setNit(ValidatorClasses.validateNitNumber(restaurant.getNit())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.INVALID_DATA_FORMAT)));
        restaurant.setPhoneNumberRestaurant(ValidatorClasses.validatePhoneNumber(restaurant.getPhoneNumberRestaurant())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.INVALID_DATA_FORMAT)));
        restaurant.setNameRestaurant(ValidatorClasses.sanitize(restaurant.getNameRestaurant())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        restaurant.setAddressRestaurant(ValidatorClasses.sanitize(restaurant.getAddressRestaurant())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        restaurant.setUrlLogo(ValidatorClasses.sanitize(restaurant.getUrlLogo())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        iRestaurantPersistencePort.saveRestaurant(restaurant);
    }

}
