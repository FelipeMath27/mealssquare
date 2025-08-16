package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.PageDTOResponse;
import com.pragma.mealssquare.application.dto.RestaurantDTORequest;
import com.pragma.mealssquare.application.dto.RestaurantDTOResponse;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.application.mapper.RestaurantRequestMapper;
import com.pragma.mealssquare.application.mapper.RestaurantResponseMapper;
import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.model.Restaurant;

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
public class RestaurantHandler implements IRestaurantHandler{

    private final IRestaurantServicePort iRestaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final IUserFeignHandler iUserFeignHandler;
    private final IUserResponseMapper iUserResponseMapper;
    private final RestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void saveListRestaurant(RestaurantDTORequest restaurantDTORequest) {
        User userOwner;
        try {
            userOwner = iUserResponseMapper.toUser(iUserFeignHandler.getUserById(restaurantDTORequest.getIdOwner()));
        } catch (UsernameNotFoundException ex) {
            throw new InfrastructureException(ConstantsErrorMessage.USER_NOT_FOUD, ex);
        }

        Restaurant newRestaurant = restaurantRequestMapper.toRestaurant(restaurantDTORequest);
        iRestaurantServicePort.saveRestaurants(newRestaurant, userOwner);
    }

    @Override
    public PageDTOResponse<RestaurantDTOResponse> getAllRestaurants(int page, int size) {
        Pagination pagination = new Pagination(page, size);
        PageResult<Restaurant> restaurantPageResult = iRestaurantServicePort.getAllRestaurants(pagination);
        return new PageDTOResponse<>(
                restaurantResponseMapper.toRestaurantDtoList(restaurantPageResult.content()),
                page,
                size,
                restaurantPageResult.totalPages(),
                restaurantPageResult.totalElements());
    }
}
