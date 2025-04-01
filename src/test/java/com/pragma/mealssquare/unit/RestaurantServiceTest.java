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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @Mock
    private IUsersMealsSquare usersMealsSquare;

    @InjectMocks
    private UseCaseRestaurant useCaseRestaurant;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_create_restaurant(){
        Rol adminRol = new Rol(1L, TypeRolEnum.ADMIN.name(),"Admin role");
        User creatorUser = new User(1L,"Tania","Supelano",
                TypeDocumentEnum.CC,"1019","+57312501",
                LocalDate.of(1992,9,8),"tania@gmail.com",
                "admin123",adminRol);
        Rol ownerRol = new Rol(2L,TypeRolEnum.OWNER.name(),"Owner Rol");
        User newUserOwner = new User(2L,"Felipe","Supelano",
                TypeDocumentEnum.CC,"1015","+57314221",
                LocalDate.of(1994,5,27),"felipe@gmail.com",
                "owner123",ownerRol);
        Restaurant restaurant = new Restaurant(1L,"food","cra21",2L,
                "+57312321","www.logo.com","9529");
        List<Restaurant> newListRestaurant = new ArrayList<>();
        newListRestaurant.add(restaurant);

        when(usersMealsSquare.getUserByEmail(anyString())).thenReturn(creatorUser);
        when(usersMealsSquare.getRoleById(anyLong())).thenReturn(ownerRol);
        when(usersMealsSquare.getUserById(anyLong())).thenReturn(newUserOwner);

        useCaseRestaurant.saveRestaurants(newListRestaurant,creatorUser.getEmail());

        verify(iRestaurantPersistencePort,times(1)).saveRestaurant(any(Restaurant.class));
    }
}
