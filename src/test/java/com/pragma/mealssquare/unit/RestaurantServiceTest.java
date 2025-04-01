package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RestaurantServiceTest {

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @InjectMocks
    private UseCaseRestaurant useCaseRestaurant;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_create_restaurant(){
        //Restaurant restaurant = new Restaurant(1L,"food","cra21",2L,);
    }
}
