package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.DishDTORequest;
import com.pragma.mealssquare.application.dto.DishDTOResponse;
import com.pragma.mealssquare.application.mapper.IDishRequestMapper;
import com.pragma.mealssquare.application.mapper.IDishResponseMapper;
import com.pragma.mealssquare.domain.api.IDishServicePort;
import com.pragma.mealssquare.domain.model.Dish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DishHandler implements IDishHandler{
    private final IDishServicePort iDishServicePort;
    private final IDishRequestMapper iDishRequestMapper;
    private final IDishResponseMapper iDishResponseMapper;

    @Override
    public void saveDish(DishDTORequest dishDTORequest, String emailOwner) {
        Dish dish = iDishRequestMapper.toDish(dishDTORequest);
        iDishServicePort.saveNewDish(dish,emailOwner);
    }

    @Override
    public DishDTOResponse getDishDTO(Long idDish) {
        return iDishResponseMapper.toResponse(iDishServicePort.getDishById(idDish));
    }
}
