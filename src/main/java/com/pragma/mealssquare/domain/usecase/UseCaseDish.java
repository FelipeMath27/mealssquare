package com.pragma.mealssquare.domain.usecase;

import com.pragma.mealssquare.domain.api.IDishServicePort;
import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.ICategoryPersistencePort;
import com.pragma.mealssquare.domain.spi.IDishPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;

import com.pragma.mealssquare.domain.validator.ValidatorClasses;
import com.pragma.mealssquare.domain.validator.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
public class UseCaseDish implements IDishServicePort {

    private final IDishPersistencePort iDishPersistencePort;
    private final ICategoryPersistencePort iCategoryPersistencePort;
    private final ValidatorService validatorService;


    @Override
    public void saveDish(Dish dish) {
        log.info(ConstantsErrorMessage.START_FLOW);
        validateCategory(dish);
        validatorService.validateRestaurantExist(dish.getRestaurant());
        processToValidateNewDish(dish);
    }

    private void validateCategory(Dish dish) {
        Long idCategory = dish.getCategory().getIdCategory() != null ? dish.getCategory().getIdCategory() : null;
        iCategoryPersistencePort.findById(idCategory)
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.CATEGORY_NOT_FOUND));
    }

    private void processToValidateNewDish(Dish newDish) {
        log.info(ConstantsErrorMessage.START_PROCESS_TO_VALIDATE_CONDITION);
        newDish.setNameDish(ValidatorClasses.sanitize(newDish.getNameDish())
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.DISH_NAME_CANT_BE_NULL)));
        newDish.setDishDescription(ValidatorClasses.sanitize(newDish.getDishDescription())
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.DISH_DESCRIPTION_CANT_BE_NULL)));
        newDish.setPriceDish(ValidatorClasses.validatePriceDish(newDish.getPriceDish()));
        newDish.setStatusDish(StatusDish.ACT);
        newDish.setUrlImageDish(ValidatorClasses.sanitize(newDish.getUrlImageDish())
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.DISH_URL_CANT_BE_NULL)));
        iDishPersistencePort.save(newDish);
    }


    @Override
    public void updateDish(User user, Dish dish) {
        log.info(ConstantsErrorMessage.START_FLOW);
        Dish dishExist = validateExistDish(dish.getIdDish());
        validateOwnerUpdateDish(user,dishExist);
        validatorService.validateRestaurantExist(dish.getRestaurant());
        if (dish.getPriceDish() != null) {
            dishExist.setPriceDish(ValidatorClasses.validatePriceDish(dish.getPriceDish()));
        }
        if (dish.getDishDescription() != null) {
            dishExist.setDishDescription(
                    ValidatorClasses.sanitize(dish.getDishDescription())
                            .orElseThrow(() -> new DomainException(ConstantsErrorMessage.DISH_DESCRIPTION_CANT_BE_NULL))
            );
        }

        iDishPersistencePort.save(dishExist);
    }

    private Dish validateExistDish(Long idDish) {
        log.info(ConstantsErrorMessage.VALIDATE_EXIST_DISH);
        return iDishPersistencePort.findById(idDish)
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.DISH_NOT_FOUND));
    }

    private void validateOwnerUpdateDish(User user, Dish dish) {
        if(!user.getIdUser().equals(dish.getRestaurant().getIdOwner())) throw new DomainException(ConstantsErrorMessage.INCORRECT_OWNER_TO_UPDATE);
    }

    @Override
    public Dish getDishById(Long idDish) {
        return null;
    }


    @Override
    public void updateDishStatus(User user,Dish dish) {
        Dish updateDish = validateExistDish(dish.getIdDish());
        validateOwnerUpdateDish(user,updateDish);
        Optional.ofNullable(dish.getStatusDish())
                .filter(newStatus -> !Objects.equals(newStatus, updateDish.getStatusDish()))
                .map(newStatus -> {
                    updateDish.setStatusDish(newStatus);
                    return updateDish;
                })
                .ifPresent(iDishPersistencePort::save);
    }

    @Override
    public List<Dish> getDishList(Long idRestaurant, int page, int size, Long idCategory) {
        Pageable pageable = PageRequest.of(page, size);
        return iDishPersistencePort.findAllByRestaurantIdAndCategoryId(idRestaurant,idCategory,pageable);
    }
}
