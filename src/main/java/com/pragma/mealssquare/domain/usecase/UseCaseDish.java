package com.pragma.mealssquare.domain.usecase;

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
    public void saveNewDish(Dish dish) {
        log.info(ConstantsErrorMessage.START_FLOW);
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
                    return iRestaurantPersistencePort.findUserById(res.getIdRestaurant());
                })
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
    }


    private void processToValidateNewDish(Dish newDish) {
        log.info(ConstantsErrorMessage.START_PROCESS_TO_VALIDATE_CONDITION);
        newDish.setNameDish(ValidatorClasses.sanitize(newDish.getNameDish())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        newDish.setDishDescription(ValidatorClasses.sanitize(newDish.getDishDescription())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        newDish.setPriceDish(ValidatorClasses.validatePriceDish(newDish.getPriceDish()));
        newDish.setStatusDish(StatusDish.ACT);
        newDish.setUrlImageDish(ValidatorClasses.sanitize(newDish.getUrlImageDish())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        iDishPersistencePort.saveDish(newDish);
    }

    @Override
    public void updateDish(Dish dish) {
        log.info(ConstantsErrorMessage.START_FLOW);
        Dish existDish = validateExistDish(dish);
        processToUpdateDish(existDish, dish);
    }

    @Override
    public void updateDishStatus(Dish dish, User user) {
        log.info(ConstantsErrorMessage.START_FLOW);
        Dish getDish = iDishPersistencePort.getDishById(dish.getIdDish());
        validateOwnerUpdateDish(getDish.getRestaurant(),user.getIdUser());
    }

    private void validateOwnerUpdateDish(Restaurant restaurant, Long idUser) {
        Optional.ofNullable(restaurant)
                .map(Restaurant::getIdOwner)
                .filter(idOwner->idOwner.equals(idUser))
                .orElseThrow(()-> new CustomException(ConstantsErrorMessage.UNAUTHORIZED_OPERATION));
    }

    private void processToUpdateDish(Dish existDish, Dish newDish) {
        existDish.setDishDescription(ValidatorClasses.sanitize(newDish.getDishDescription())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CANT_BE_NULL)));
        existDish.setPriceDish(ValidatorClasses.validatePriceDish(newDish.getPriceDish()));
        iDishPersistencePort.saveDish(existDish);
    }


    private Dish validateExistDish(Dish dish) {
        log.info(ConstantsErrorMessage.VALIDATE_EXIST_DISH);
        return Optional.ofNullable(dish)
                .map(Dish::getIdDish)
                .map(iDishPersistencePort::getDishById)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.DISH_NOT_FOUND));
    }

    @Override
    public Dish getDishById(Long idDish) {
        return null;
    }
}
