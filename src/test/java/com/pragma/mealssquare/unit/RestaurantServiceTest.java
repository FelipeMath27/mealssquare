package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseRestaurant;
import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @InjectMocks
    private UseCaseRestaurant useCaseRestaurant;

    private User creatorUser, ownerUser;
    private Restaurant restaurant;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        Rol ownerRole = new Rol(1L, TypeRolEnum.OWNER.name(), "Rol owner");
        Rol adminRole = new Rol(2L, TypeRolEnum.ADMIN.name(), "Admin rol");

        creatorUser = new User(1L, "felipe", "mesa",TypeDocumentEnum.CC,"1015141",
                "+57321321",LocalDate.of(1994,5,27),"felipe@gmail.com",
                "231321", adminRole);
        ownerUser = new User(2L, "Tania", "mesa", TypeDocumentEnum.CC, "1019",
                "+57323231", LocalDate.of(1992, 9, 8), "tania@gmail.com",
                "0000", ownerRole);

        restaurant = new Restaurant(1L, "RestaurantName", "RestaurantAddress",
                ownerUser.getIdUser(),"+57321312", "www.uno.com","123456789");
    }

    @Test
    void test_create_restaurant(){
        when(iRestaurantPersistencePort.getUserByEmail(creatorUser.getEmail())).thenReturn(creatorUser);
        when(iRestaurantPersistencePort.getUserById(restaurant.getIdOwner())).thenReturn(ownerUser);
        useCaseRestaurant.saveRestaurants(restaurant, creatorUser.getEmail());
        verify(iRestaurantPersistencePort,times(1)).saveRestaurant(any(Restaurant.class));
    }

    /** @Test
    void test_create_restaurant_two() {
        assertDoesNotThrow(() -> useCaseRestaurant.saveRestaurants(restaurant,creatorUser.getEmail()));
        verify(iRestaurantPersistencePort, times(1)).saveRestaurant(any(Restaurant.class));
    }*/

}
