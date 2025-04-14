package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.domain.api.ICategoryServicePort;
import com.pragma.mealssquare.domain.api.IRestaurantServicePort;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.ICategoryPersistencePort;
import com.pragma.mealssquare.domain.spi.IDishPersistencePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseDish;
import com.pragma.mealssquare.domain.usecase.UseCaseRestaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class DishServiceTest {

    @Mock
    private IDishPersistencePort iDishPersistencePort;

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @Mock
    private ICategoryPersistencePort iCategoryPersistencePort;

    @InjectMocks
    private UseCaseDish useCaseDish;

    private Dish newDish;
    private User user;
    private Category category;
    private Restaurant restaurant;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        Rol rol = new Rol(1L, TypeRolEnum.OWNER.name(), "Rol owner");
        category = new Category(1L, "FAST FOOD", "Any fast food");
        user = new User(2L, "Tania", "mesa", TypeDocumentEnum.CC, "1019",
                "+57323231", LocalDate.of(1992, 9, 8), "tania@gmail.com",
                "0000", rol);
        restaurant = new Restaurant(1L, "RestaurantName", "RestaurantAddress",
                user.getRol().getIdRol(), "+57321312", "www.uno.com", "123456789");
        newDish = new Dish(1L,"Vegetarina Pizza", category,"pizza with mushrooms, onion, cheese and tomato"
                ,10.0, restaurant,"www.pizza.com",null);
    }

    @Test
    void test_crete_new_dish(){
        when(iRestaurantPersistencePort.getUserByEmail(user.getEmail())).thenReturn(user);
        when(iCategoryPersistencePort.getCategoryId(category.getIdCategory())).thenReturn(category);
        when(iRestaurantPersistencePort.getRestaurantById(restaurant.getIdRestaurant())).thenReturn(restaurant);
        useCaseDish.saveNewDish(newDish, user.getEmail());
        verify(iDishPersistencePort,times(1)).saveDish(any(Dish.class));
    }
}
