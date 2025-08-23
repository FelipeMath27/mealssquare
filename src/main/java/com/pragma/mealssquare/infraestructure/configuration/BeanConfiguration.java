package com.pragma.mealssquare.infraestructure.configuration;

import com.pragma.mealssquare.domain.api.*;
import com.pragma.mealssquare.domain.spi.*;
import com.pragma.mealssquare.domain.usecase.*;

import com.pragma.mealssquare.domain.validator.ValidatorService;
import com.pragma.mealssquare.infraestructure.output.mapper.IDishEntityMapper;
import com.pragma.mealssquare.infraestructure.output.mapper.RestaurantEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IDishRepository;
import com.pragma.mealssquare.infraestructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BeanConfiguration {

    private final IRestaurantRepository iRestaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository iDishRepository;
    private final IDishEntityMapper iDishEntityMapper;

    @Bean
    public IRestaurantServicePort iRestaurantServicePort(IRestaurantPersistencePort iRestaurantPersistencePort){
        return new UseCaseRestaurant(iRestaurantPersistencePort);
    }

    @Bean
    public IDishServicePort iDishServicePort(IDishPersistencePort iDishPersistencePort,
                                             ICategoryPersistencePort iCategoryPersistencePort, ValidatorService validatorService){
       return new UseCaseDish(iDishPersistencePort,iCategoryPersistencePort,validatorService);
    }

    @Bean
    public ICategoryServicePort iCategoryServicePort(ICategoryPersistencePort iCategoryPersistencePort){
        return new UseCaseCategory(iCategoryPersistencePort);
    }

    @Bean
    public ValidatorService validatorService(IRestaurantPersistencePort iRestaurantPersistencePort) {
        return new ValidatorService(iRestaurantPersistencePort);
    }

    @Bean
    public IEmployeeServicePort iEmployeeServicePort(IRestaurantPersistencePort iRestaurantPersistencePort,
                                                              IEmployeePersistencePort iEmployeePersistencePort,
                                                              ValidatorService validatorService) {
        return new UseCaseEmployee(iRestaurantPersistencePort, validatorService, iEmployeePersistencePort);
    }

    @Bean
    public IOrderServicePort iOrderServicePort(IOrderPersistencePort iOrderPersistencePort,
                                               IRestaurantPersistencePort iRestaurantPersistencePort,
                                               IDishPersistencePort iDishPersistencePort,
                                               IEmployeePersistencePort iEmployeePersistencePort){
        return new UseCaseOrder(iRestaurantPersistencePort, iOrderPersistencePort, iDishPersistencePort,iEmployeePersistencePort);
    }

}
