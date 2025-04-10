package com.pragma.mealssquare.domain.usecase;

import com.pragma.mealssquare.domain.api.IDishServicePort;
import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UseCaseDish implements IDishServicePort {
    private final IUsersMealsSquare iUsersMealsSquare;

    @Override
    public void saveNewDish(Dish newDish, String emailOwner) {
        log.info(ConstantsErrorMessage.START_FLOW);
        User ownerUser = iUsersMealsSquare.getUserByEmail(emailOwner);
    }
}
