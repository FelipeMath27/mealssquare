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

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class UseCaseRestaurant implements IRestaurantServicePort {

    private static final Logger logger = LoggerFactory.getLogger(UseCaseRestaurant.class);

    private final IRestaurantPersistencePort iRestaurantPersistencePort;

    @Override
    public void saveRestaurants(Restaurant restaurant) {
        log.info(ConstantsErrorMessage.START_FLOW);
        User userOwnerRestaurant = iRestaurantPersistencePort.findUserById(restaurant.getIdOwner())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.USER_NOT_FOUD));
        ValidatorClasses.validateOwner(userOwnerRestaurant);
        log.info(ConstantsErrorMessage.END_VALIDATE_OWNER);
        processValidateSaveRestaurant(restaurant);
    }

    private void processValidateSaveRestaurant(Restaurant restaurant){
        log.info(ConstantsErrorMessage.START_PROCESS_TO_VALIDATE_CONDITION);
        restaurant.setNameRestaurant(ValidatorClasses.validateRestaurantName(restaurant.getNameRestaurant())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.INVALID_RESTAURANT_NAME_FORMAT)));
        restaurant.setNit(ValidatorClasses.validateNitNumber(restaurant.getNit())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.INVALID_FORMAT_NIT)));
        restaurant.setPhoneNumberRestaurant(ValidatorClasses.validatePhoneNumber(restaurant.getPhoneNumberRestaurant())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.INVALID_FORMAT_PHONE)));
        restaurant.setAddressRestaurant(ValidatorClasses.sanitize(restaurant.getAddressRestaurant())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        restaurant.setUrlLogo(ValidatorClasses.sanitize(restaurant.getUrlLogo())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        iRestaurantPersistencePort.save(restaurant);
        log.info(ConstantsErrorMessage.END_SUCCESSFUL_FLOW);
    }

    @Override
    public Restaurant getRestaurantByNit(String nitRestaurant) {
        String nitSatinized = ValidatorClasses.sanitize(nitRestaurant)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL));
        return iRestaurantPersistencePort.findRestaurantByNit(nitSatinized)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
    }

    @Override
    public Restaurant getRestaurantById(Long idRestaurant) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }
}
