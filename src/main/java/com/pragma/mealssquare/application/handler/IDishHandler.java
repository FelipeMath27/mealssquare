package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.DishDTORequest;
import com.pragma.mealssquare.domain.model.Dish;

public interface IDishHandler {
    void saveDish (DishDTORequest dishDTORequest, String emailOwner);
}
