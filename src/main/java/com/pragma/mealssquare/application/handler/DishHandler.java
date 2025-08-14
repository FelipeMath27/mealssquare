package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.DishDTORequest;
import com.pragma.mealssquare.application.dto.DishDTOResponse;
import com.pragma.mealssquare.application.dto.DishDTOStatusRequest;
import com.pragma.mealssquare.application.dto.DishUpdateDTORequest;
import com.pragma.mealssquare.application.mapper.IDishRequestMapper;
import com.pragma.mealssquare.application.mapper.IDishResponseMapper;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.domain.api.IDishServicePort;
import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DishHandler implements IDishHandler{
    private final IDishServicePort iDishServicePort;
    private final IDishRequestMapper iDishRequestMapper;
    private final IDishResponseMapper iDishResponseMapper;
    private final IUserFeignHandler iUserFeignHandler;
    private final IUserResponseMapper iUserResponseMapper;

    @Override
    public void saveDish(DishDTORequest dishDTORequest) {
        Dish dish = iDishRequestMapper.toDish(dishDTORequest);
        iDishServicePort.saveDish(dish);
    }

    @Override
    public DishDTOResponse getDishDTO(Long idDish) {
        return iDishResponseMapper.toResponse(iDishServicePort.getDishById(idDish));
    }

    @Override
    public void updateDish(DishUpdateDTORequest dishUpdateDTORequest, String email) {
        User userOwner;
        try {
            userOwner = iUserResponseMapper.toUser(iUserFeignHandler.getUserByEmail(email));
        } catch (UsernameNotFoundException ex) {
            throw new InfrastructureException(ConstantsErrorMessage.USER_NOT_FOUD, ex);
        }
        iDishServicePort.updateDish(userOwner,iDishRequestMapper.toDishUpdate(dishUpdateDTORequest));
    }

    @Override
    public void updateStatusDish(DishDTOStatusRequest dishDTOStatusRequest, String emailOwner) {
        User userOwner;
        try {
            userOwner = iUserResponseMapper.toUser(iUserFeignHandler.getUserByEmail(emailOwner));
        } catch (UsernameNotFoundException ex) {
            throw new InfrastructureException(ConstantsErrorMessage.USER_NOT_FOUD, ex);
        }
        iDishServicePort.updateDishStatus(userOwner,iDishRequestMapper.toDishStatusUpdate(dishDTOStatusRequest));
    }

    @Override
    public List<DishDTOResponse> getListDishes(Long idRestaurant, int page, int size, Long idCategory) {
        List<Dish> dishList = iDishServicePort.getDishList(idRestaurant, page, size, idCategory);
        return iDishResponseMapper.toDishDtoList(dishList);
    }
}
