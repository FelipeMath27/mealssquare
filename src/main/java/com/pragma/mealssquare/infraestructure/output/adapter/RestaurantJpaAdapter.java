package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import com.pragma.mealssquare.infraestructure.output.entity.RestaurantEntity;
import com.pragma.mealssquare.infraestructure.output.mapper.RestaurantEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository iRestaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Autowired
    public RestaurantJpaAdapter(IRestaurantRepository iRestaurantRepository,
                                RestaurantEntityMapper restaurantEntityMapper) {
        this.iRestaurantRepository = iRestaurantRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toRestaurantEntity(restaurant);
        iRestaurantRepository.save(restaurantEntity);
    }
}
