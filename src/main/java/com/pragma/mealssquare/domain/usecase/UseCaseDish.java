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


@RequiredArgsConstructor
@Slf4j
public class UseCaseDish implements IDishServicePort {

    private final IDishPersistencePort iDishPersistencePort;
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final ICategoryPersistencePort iCategoryPersistencePort;

    @Override
    public void saveDish(Dish dish) {
        log.info(ConstantsErrorMessage.START_FLOW);
        validateCategory(dish);
        validateRestaurantExist(dish.getRestaurant());
        processToValidateNewDish(dish);
    }

    private void validateCategory(Dish dish) {
        Long idCategory = dish.getCategory().getIdCategory() != null ? dish.getCategory().getIdCategory() : null;
        iCategoryPersistencePort.findById(idCategory)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.CATEGORY_NOT_FOUND));
    }

    private void validateRestaurantExist(Restaurant restaurant){
        Long idRestaurant = restaurant.getIdRestaurant() != null ? restaurant.getIdRestaurant() : null;
        iRestaurantPersistencePort.findRestaurantById(idRestaurant)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
    }

    private void processToValidateNewDish(Dish newDish) {
        log.info(ConstantsErrorMessage.START_PROCESS_TO_VALIDATE_CONDITION);
        newDish.setNameDish(ValidatorClasses.sanitize(newDish.getNameDish())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.DISH_NAME_CANT_BE_NULL)));
        newDish.setDishDescription(ValidatorClasses.sanitize(newDish.getDishDescription())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.DISH_DESCRIPTION_CANT_BE_NULL)));
        newDish.setPriceDish(ValidatorClasses.validatePriceDish(newDish.getPriceDish()));
        newDish.setStatusDish(StatusDish.ACT);
        newDish.setUrlImageDish(ValidatorClasses.sanitize(newDish.getUrlImageDish())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.DISH_URL_CANT_BE_NULL)));
        iDishPersistencePort.save(newDish);
    }

    /**@Override
    public void updateDishStatus(Dish dish, User user) {
        log.info(ConstantsErrorMessage.START_FLOW);
        Optional<Dish> getDish = iDishPersistencePort.findById(dish.getIdDish());
        validateOwnerUpdateDish();
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
        iDishPersistencePort.save(existDish);
    }


    private Optional<Dish> validateExistDish(Dish dish) {
        log.info(ConstantsErrorMessage.VALIDATE_EXIST_DISH);
        return Optional.ofNullable(dish)
                .map(Dish::getIdDish)
                .map(iDishPersistencePort::findById)
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.DISH_NOT_FOUND));
    }*/

    @Override
    public Dish getDishById(Long idDish) {
        return null;
    }

    @Override
    public void updateDish(Dish dish) {
        // TODO document why this method is empty
    }

    @Override
    public void updateDishStatus(Dish dish, User user) {
        // TODO document why this method is empty
    }
}
