package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import com.pragma.mealssquare.infraestructure.exceptions.MicroserviceConnectionException;
import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import com.pragma.mealssquare.infraestructure.output.entity.RestaurantEntity;
import com.pragma.mealssquare.infraestructure.output.mapper.RestaurantEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IRestaurantRepository;

import jakarta.persistence.PersistenceException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository iRestaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IUsersMealsSquare iUsersMealsSquare;

    RestaurantEntity restaurantEntity;

    @Override
    public void save(Restaurant restaurant) {
        try {
            iRestaurantRepository.save(restaurantEntityMapper.toRestaurantEntity(restaurant));
        } catch (DataAccessException | PersistenceException ex){
            throw new InfrastructureException(ConstantsErrorMessage.RESTAURANT_NOT_SAVED, ex);
        }
    }

    @Override
    public Optional<Restaurant> findRestaurantByNit(String nitRestaurant) {
        Optional<RestaurantEntity> userEntityOptional = iRestaurantRepository.findByNit(nitRestaurant);
        return Optional.ofNullable(userEntityOptional.map(restaurantEntityMapper::toRestaurant).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ConstantsErrorMessage.RESTAURANT_NOT_FOUND)));
    }

    @Override
    public Optional<Restaurant> findRestaurantById(Long idRestaurant) {
        return iRestaurantRepository.findById(idRestaurant)
                .map(restaurantEntityMapper::toRestaurant);
    }

    @Override
    public PageResult<Restaurant> getAllRestaurants(Pagination pagination) {
        Pageable pageable = PageRequest.of(
                pagination.page(),
                pagination.size(),
                Sort.by("nameRestaurant").ascending()
        );

        Page<RestaurantEntity> page = iRestaurantRepository.findAllByOrderByNameRestaurantAsc(pageable);
        List<Restaurant> content = restaurantEntityMapper.toRestaurantList(page.getContent());

        return new PageResult<>(
                content,
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}
