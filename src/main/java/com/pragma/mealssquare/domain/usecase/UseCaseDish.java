package com.pragma.mealssquare.domain.usecase;

import com.pragma.mealssquare.domain.api.ICategoryServicePort;
import com.pragma.mealssquare.domain.api.IDishServicePort;
import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.IDishPersistencePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;

import com.pragma.mealssquare.domain.validator.ValidatorClasses;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class UseCaseDish implements IDishServicePort {

    private final IDishPersistencePort iDishPersistencePort;
    private final IRestaurantServicePort iRestaurantServicePort;
    private final ICategoryServicePort iCategoryServicePort;

    @Override
    public void saveNewDish(Dish newDish, String emailOwner) {
        log.info(ConstantsErrorMessage.START_FLOW);
        ValidatorClasses.validateOwner(iRestaurantServicePort.getUserByEmail(emailOwner));
        validateCategory(newDish);
        validateRestaurantExist(newDish.getRestaurant());
        processToValidateNewDish(newDish);
    }

    private void validateCategory(Dish dish) {
        Optional.ofNullable(dish.getCategory())
                        .map(category -> {
                            return iCategoryServicePort.getCategoryId(category.getIdCategory());
                        })
                .ifPresentOrElse(
                        dish::setCategory,
                        () -> {
                            throw new CustomException(ConstantsErrorMessage.CATEGORY_NOT_FOUND);
                        });
    }

    private void validateRestaurantExist(Restaurant restaurant){
        Optional.ofNullable(restaurant)
                .map(res -> {
                    return iRestaurantServicePort.getRestaurantByNit(restaurant.getNit());
                })
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
    }


    private void processToValidateNewDish(Dish newDish) {
        newDish.setNameDish(ValidatorClasses.sanitize(newDish.getNameDish())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        newDish.setDescriptionDish(ValidatorClasses.sanitize(newDish.getDescriptionDish())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        newDish.setPrice(ValidatorClasses.validatePriceDish(newDish.getPrice()));
        newDish.setStatusDish(StatusDish.AVAILABLE);
        newDish.setUrlImage(ValidatorClasses.sanitize(newDish.getUrlImage())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        iDishPersistencePort.saveDish(newDish);
    }
}
