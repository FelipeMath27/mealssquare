package com.pragma.mealssquare.domain.usecase;

import com.pragma.mealssquare.domain.api.ICategoryServicePort;
import com.pragma.mealssquare.domain.api.IDishServicePort;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.ICategoryPersistencePort;
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
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final ICategoryPersistencePort iCategoryPersistencePort;

    @Override
    public void saveNewDish(Dish dish, String emailOwner) {
        log.info(ConstantsErrorMessage.START_FLOW);
        ValidatorClasses.sanitize(emailOwner);
        log.info("this is the email {}", emailOwner);
        User user = iRestaurantPersistencePort.getUserByEmail(emailOwner);
        log.info("This is the user {}", user.getRol());
        ValidatorClasses.validateOwner(user);
        log.info("This is the id category {}",dish.getCategory().getIdCategory());
        validateCategory(dish);
        validateRestaurantExist(dish.getRestaurant());
        processToValidateNewDish(dish);
    }

    private void validateCategory(Dish dish) {
        Optional.ofNullable(dish.getCategory())
                        .map(category -> {
                            return iCategoryPersistencePort.getCategoryId(category.getIdCategory());
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
                    return iRestaurantPersistencePort.getRestaurantById(res.getIdRestaurant());
                })
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
    }


    private void processToValidateNewDish(Dish newDish) {
        log.info("this is the name: {}", newDish.getNameDish());
        newDish.setNameDish(ValidatorClasses.sanitize(newDish.getNameDish())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        log.info("this is the name: {}", newDish.getDishDescription());
        newDish.setDishDescription(ValidatorClasses.sanitize(newDish.getDishDescription())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        log.info("this is the name: {}", newDish.getPriceDish());
        newDish.setPriceDish(ValidatorClasses.validatePriceDish(newDish.getPriceDish()));
        newDish.setStatusDish(StatusDish.ACT);
        newDish.setUrlImageDish(ValidatorClasses.sanitize(newDish.getUrlImageDish())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        iDishPersistencePort.saveDish(newDish);
    }

    @Override
    public Dish getDishById(Long idDish) {
        return null;
    }
}
