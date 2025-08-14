package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.DishDTORequest;
import com.pragma.mealssquare.application.dto.DishDTOResponse;
import com.pragma.mealssquare.application.dto.DishDTOStatusRequest;
import com.pragma.mealssquare.application.dto.DishUpdateDTORequest;

import java.util.List;

public interface IDishHandler {
    void saveDish (DishDTORequest dishDTORequest);

    void updateDish(DishUpdateDTORequest dishUpdateDTORequest, String email);

    DishDTOResponse getDishDTO(Long idDish);

    void updateStatusDish(DishDTOStatusRequest dishDTOStatusRequest, String email);

    List<DishDTOResponse> getListDishes(Long idRestaurant, int page, int size, Long idCategory);
}
