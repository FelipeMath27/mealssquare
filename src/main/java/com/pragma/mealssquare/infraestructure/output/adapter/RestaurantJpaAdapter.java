package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import com.pragma.mealssquare.infraestructure.output.entity.RestaurantEntity;
import com.pragma.mealssquare.infraestructure.output.mapper.RestaurantEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IRestaurantRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository iRestaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    RestaurantEntity restaurantEntity;
    @Override
    public void saveRestaurant(Restaurant restaurant) {
        try {
            iRestaurantRepository.save(restaurantEntity = restaurantEntityMapper.toRestaurantEntity(restaurant));
        } catch (DataAccessException | PersistenceException ex){
            throw new InfrastructureException("Failed to save the new owner", ex);
        }
    }
}
