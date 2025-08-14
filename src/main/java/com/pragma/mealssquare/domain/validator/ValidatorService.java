package com.pragma.mealssquare.domain.validator;


import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidatorService {
    private final IRestaurantPersistencePort iRestaurantPersistencePort;

    public void validateRestaurantExist(Restaurant restaurant){
        Long idRestaurant = restaurant.getIdRestaurant() != null ? restaurant.getIdRestaurant() : null;
        iRestaurantPersistencePort.findRestaurantById(idRestaurant)
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
    }
}
