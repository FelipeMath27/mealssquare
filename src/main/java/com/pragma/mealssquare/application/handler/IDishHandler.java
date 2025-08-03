package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.DishDTORequest;
import com.pragma.mealssquare.application.dto.DishDTOResponse;
import com.pragma.mealssquare.application.dto.DishDTOStatusRequest;
import com.pragma.mealssquare.application.dto.DishUpdateDTORequest;

public interface IDishHandler {
    void saveDish (DishDTORequest dishDTORequest);

    void updateDish(DishUpdateDTORequest dishUpdateDTORequest, String email);

    DishDTOResponse getDishDTO(Long idDish);

    void updateStatusDish(DishDTOStatusRequest dishDTOStatusRequest, String token);
}
