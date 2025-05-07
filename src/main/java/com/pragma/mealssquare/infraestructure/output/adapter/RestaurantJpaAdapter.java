package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.OptionalInt;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository iRestaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IUsersMealsSquare iUsersMealsSquare;

    RestaurantEntity restaurantEntity;
    @Override
    public void saveRestaurant(Restaurant restaurant) {
        try {
            iRestaurantRepository.save(restaurantEntity = restaurantEntityMapper.toRestaurantEntity(restaurant));
        } catch (DataAccessException | PersistenceException ex){
            throw new InfrastructureException(ConstantsErrorMessage.RESTAURANT_NOT_SAVED, ex);
        }
    }

    @Override
    public Restaurant getRestaurantByNit(String nitRestaurant) {
        Optional<RestaurantEntity> userEntityOptional = iRestaurantRepository.findByNit(nitRestaurant);
        return userEntityOptional.map(restaurantEntityMapper::toRestaurant).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
    }

    @Override
    public User getUserByEmail(String email) {
        return Optional.ofNullable(email)
                .map(iUsersMealsSquare::getUserByEmail)
                .orElseThrow(() -> new MicroserviceConnectionException(ConstantsErrorMessage.CANT_CONNECT_MICROSERVICES));
    }

    @Override
    public User getUserById(Long id) {
        return Optional.ofNullable(id)
                .map(iUsersMealsSquare::getUserById)
                .orElseThrow(() -> new MicroserviceConnectionException(ConstantsErrorMessage.CANT_CONNECT_MICROSERVICES));
    }

    @Override
    public Restaurant getRestaurantById(Long idRestaurant) {
        Optional<RestaurantEntity> restaurantEntityOptional = iRestaurantRepository.findById(idRestaurant);
        return  restaurantEntityOptional.map(restaurantEntityMapper::toRestaurant)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
    }
}
