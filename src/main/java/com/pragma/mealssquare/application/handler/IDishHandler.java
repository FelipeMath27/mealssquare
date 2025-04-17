package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.DishDTORequest;
import com.pragma.mealssquare.application.dto.DishDTOResponse;
import com.pragma.mealssquare.application.dto.DishUpdateDTORequest;

public interface IDishHandler {
    void saveDish (DishDTORequest dishDTORequest, String emailOwner);

    void updateDish(DishUpdateDTORequest dishUpdateDTORequest, String emailOwner);

    DishDTOResponse getDishDTO(Long ifDish);
}
