package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.ICategoryPersistencePort;
import com.pragma.mealssquare.domain.spi.IDishPersistencePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseDish;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        when(iRestaurantPersistencePort.getUserByEmail(user.getEmail())).thenReturn(user);
        when(iCategoryPersistencePort.getCategoryId(category.getIdCategory())).thenReturn(category);
        when(iRestaurantPersistencePort.getRestaurantById(restaurant.getIdRestaurant())).thenReturn(restaurant);
        when(iDishPersistencePort.getDishById(newDish.getIdDish())).thenReturn(newDish);
    }

    @Test
    void test_crete_new_dish(){
        useCaseDish.saveNewDish(newDish);
        verify(iDishPersistencePort,times(1)).saveDish(any(Dish.class));
    }

    @Test
    void test_create_new_dish_with_less_than_zero(){
        newDish.setPriceDish(-1.0);
        CustomException customException = assertThrows(CustomException.class, ()->{
            useCaseDish.saveNewDish(newDish);
        });

        assertEquals(ConstantsErrorMessage.PRICE_MUST_BE_GREATER_THAN, customException.getMessage());

        verify(iDishPersistencePort,never()).saveDish(any(Dish.class));
    }

    @Test
    void test_create_dish_without_name(){
        newDish.setNameDish(null);
        CustomException exception = assertThrows(CustomException.class,()->{
            useCaseDish.saveNewDish(newDish);
        });

        assertEquals(ConstantsErrorMessage.CANT_BE_NULL,exception.getMessage());

        verify(iDishPersistencePort,never()).saveDish(any(Dish.class));
    }

    @Test
    void test_create_dish_without_description(){
        newDish.setDishDescription(null);
        CustomException exception = assertThrows(CustomException.class,()->{
            useCaseDish.saveNewDish(newDish);
        });

        assertEquals(ConstantsErrorMessage.CANT_BE_NULL,exception.getMessage());

        verify(iDishPersistencePort,never()).saveDish(any(Dish.class));
    }

    /** Start test to update dish*/
    @Test
    void test_validate_correct_update_dish(){
        newDish.setPriceDish(14.5);
        newDish.setDishDescription("This updated is for an extra meet in the pizza");
        useCaseDish.updateDish(newDish);
        verify(iDishPersistencePort,times(1)).saveDish(any(Dish.class));
    }

    @Test
    void test_validate_correct_update_dish_by_price(){
        newDish.setPriceDish(14.5);
        useCaseDish.updateDish(newDish);
        verify(iDishPersistencePort,times(1)).saveDish(any(Dish.class));
    }

    @Test
    void test_validate_correct_update_dish_by_description(){
        newDish.setDishDescription("This updated is for an extra meet in the pizza");
        useCaseDish.updateDish(newDish);
        verify(iDishPersistencePort,times(1)).saveDish(any(Dish.class));
    }

    @Test
    void test_not_found_dish_to_update(){
        newDish.setIdDish(2L);
        when(iDishPersistencePort.getDishById(newDish.getIdDish())).thenReturn(null);
        CustomException catchException = assertThrows(CustomException.class,
                ()-> useCaseDish.updateDish(newDish));

        assertEquals(ConstantsErrorMessage.DISH_NOT_FOUND,catchException.getMessage());
        verify(iDishPersistencePort,never()).saveDish(newDish);
    }

}
