package com.pragma.mealssquare.infraestructure.configuration;

import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseRestaurant;

import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import com.pragma.mealssquare.infraestructure.output.mapper.RestaurantEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IRestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    private final IRestaurantRepository iRestaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    public BeanConfiguration(IRestaurantRepository iRestaurantRepository, RestaurantEntityMapper restaurantEntityMapper) {
        this.iRestaurantRepository = iRestaurantRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
    }

    @Bean
    public IRestaurantServicePort iRestaurantServicePort(IRestaurantPersistencePort iRestaurantPersistencePort,
                                                         IUsersMealsSquare usersMealsSquare){
        return new UseCaseRestaurant(iRestaurantPersistencePort, usersMealsSquare);
    }
}
