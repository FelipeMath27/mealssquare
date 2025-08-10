package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.application.handler.IUserFeignHandler;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.ICategoryPersistencePort;
import com.pragma.mealssquare.domain.spi.IDishPersistencePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseDish;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.domain.validator.ValidatorService;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DishServiceTest {

    @Mock
    private IDishPersistencePort iDishPersistencePort;

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @Mock
    private ICategoryPersistencePort iCategoryPersistencePort;

    @Mock
    private IUserFeignHandler iUserFeignHandler;

    @Mock
    private IUserResponseMapper iUserResponseMapper;

    @Mock
    private ValidatorService validatorService;

    @InjectMocks
    private UseCaseDish useCaseDish;

    private Dish newDish;
    private User user;
    private Category category;
    private Restaurant restaurant;
    private CustomException customException;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        Rol rol = new Rol(1L, TypeRolEnum.OWNER.name(), "Rol owner");
        category = new Category(1L, "FAST FOOD", "Any fast food");
        user = new User(2L, "Tania", "mesa", TypeDocumentEnum.CC, "1019",
                "+57323231", LocalDate.of(1992, 9, 8), "tania@gmail.com",
                "0000", rol);
        restaurant = new Restaurant(1L, "RestaurantName", "RestaurantAddress",
                user.getIdUser(), "+57321312", "www.uno.com", "123456789");
        newDish = new Dish(1L,"Vegetarina Pizza", category,"pizza with mushrooms, onion, cheese and tomato"
                ,10.0, restaurant,"www.pizza.com",null);
        when(iUserFeignHandler.getUserByEmail(user.getEmail())).thenReturn(new UserDTOResponse());
        when(iUserResponseMapper.toUser(any(UserDTOResponse.class))).thenReturn(user);
        when(iCategoryPersistencePort.findById(category.getIdCategory())).thenReturn(Optional.ofNullable(category));
        when(iRestaurantPersistencePort.findRestaurantById(restaurant.getIdRestaurant())).thenReturn(Optional.ofNullable(restaurant));
        when(iDishPersistencePort.findById(newDish.getIdDish())).thenReturn(Optional.ofNullable(newDish));
        doNothing().when(validatorService).validateRestaurantExist(any(Restaurant.class));
    }

    @Test
    void test_crete_new_dish(){
        useCaseDish.saveDish(newDish);
        verify(iDishPersistencePort,times(1)).save(any(Dish.class));
    }

    @Test
    void test_create_dish_with_wrong_price(){
        newDish.setPriceDish(-1.0);
        customException = assertThrows(CustomException.class,() -> useCaseDish.saveDish(newDish));
        assertEquals(ConstantsErrorMessage.PRICE_MUST_BE_GREATER_THAN, customException.getMessage());
        verify(iDishPersistencePort,never()).save(any(Dish.class));
    }

    @Test
    void test_create_dish_without_name(){
        newDish.setNameDish(null);
        customException = assertThrows(CustomException.class,() -> useCaseDish.saveDish(newDish));
        assertEquals(ConstantsErrorMessage.DISH_NAME_CANT_BE_NULL, customException.getMessage());
        verify(iDishPersistencePort,never()).save(any(Dish.class));
    }

    @Test
    void test_create_dish_without_description(){
        newDish.setDishDescription(null);
        customException = assertThrows(CustomException.class,() -> useCaseDish.saveDish(newDish));
        assertEquals(ConstantsErrorMessage.DISH_DESCRIPTION_CANT_BE_NULL,customException.getMessage());
        verify(iDishPersistencePort,never()).save(any(Dish.class));
    }

    @Test
    void test_create_dish_with_non_restaurant_exist() {
        newDish.getRestaurant().setIdRestaurant(10L);
        when(iRestaurantPersistencePort.findRestaurantById(10L))
                .thenReturn(Optional.empty());
        verify(iDishPersistencePort, never()).save(any(Dish.class));
    }

    @Test
    void test_create_dish_with_status_inactive() {
        newDish.setStatusDish(StatusDish.INA);
        useCaseDish.saveDish(newDish);
        verify(iDishPersistencePort, times(1)).save(any(Dish.class));
        assertEquals(StatusDish.ACT, newDish.getStatusDish());
    }

    /** Start test to update dish*/
    @Test
    void test_update_dish(){
        newDish.setPriceDish(14.5);
        newDish.setDishDescription("This updated is for an extra meet in the pizza");
        useCaseDish.updateDish(user,newDish);
        verify(iDishPersistencePort,times(1)).save(argThat(updatedDish ->
                updatedDish.getIdDish().equals(newDish.getIdDish()) &&
                updatedDish.getDishDescription().equals("This updated is for an extra meet in the pizza") &&
                updatedDish.getPriceDish().equals(14.5)
                ));
    }

    @Test
    void test_validate_correct_update_dish_by_price(){
        newDish.setPriceDish(14.5);
        useCaseDish.updateDish(user,newDish);
        verify(iDishPersistencePort,times(1)).save(any(Dish.class));
    }

    @Test
    void test_validate_correct_update_dish_by_description(){
        newDish.setDishDescription("This updated is for an extra meet in the pizza");
        useCaseDish.updateDish(user,newDish);
        verify(iDishPersistencePort,times(1)).save(any(Dish.class));
    }

    @Test
    void test_not_found_dish_to_update(){
        when(iDishPersistencePort.findById(newDish.getIdDish())).thenReturn(Optional.empty());
        customException = assertThrows(CustomException.class, ()-> useCaseDish.updateDish(user,newDish));
        assertEquals(ConstantsErrorMessage.DISH_NOT_FOUND,customException.getMessage());
        verify(iDishPersistencePort,never()).save(any(Dish.class));
    }

    @Test
    void test_update_dish_non_owner_restaurant_dish(){
        newDish.getRestaurant().setIdOwner(10L);
        customException = assertThrows(CustomException.class, () -> useCaseDish.updateDish(user,newDish));
        assertEquals(ConstantsErrorMessage.INCORRECT_OWNER_TO_UPDATE,customException.getMessage());
        verify(iDishPersistencePort,never()).save(any(Dish.class));
    }
}
