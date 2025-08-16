package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.*;
import com.pragma.mealssquare.application.mapper.IDishRequestMapper;
import com.pragma.mealssquare.application.mapper.IDishResponseMapper;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.domain.api.IDishServicePort;
import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public PageDTOResponse<DishDTOResponse> getDishesByIdRestaurant(Long idRestaurant, int page, int size, Long idCategory) {
        Pagination pagination = new Pagination(page, size);
        PageResult<Dish> pageResult = iDishServicePort.getDishList(idRestaurant,idCategory,pagination);
        return new PageDTOResponse<>(
                iDishResponseMapper.toDishDtoList(pageResult.content()),
                page,
                size,
                pageResult.totalPages(),
                pageResult.totalElements()
        );
    }
}
