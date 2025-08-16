package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.*;


public interface IDishHandler {
    void saveDish (DishDTORequest dishDTORequest);

    void updateDish(DishUpdateDTORequest dishUpdateDTORequest, String email);

    DishDTOResponse getDishDTO(Long idDish);

    void updateStatusDish(DishDTOStatusRequest dishDTOStatusRequest, String email);

    PageDTOResponse<DishDTOResponse> getDishesByIdRestaurant(Long idRestaurant, int page, int size, Long idCategory);
}
