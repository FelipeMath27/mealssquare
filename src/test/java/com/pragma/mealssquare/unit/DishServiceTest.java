package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.application.handler.IUserFeignHandler;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.ICategoryPersistencePort;
import com.pragma.mealssquare.domain.spi.IDishPersistencePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseDish;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.domain.validator.ValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.util.List;
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

    private Dish newDish,dishUpadated;
    private User user;
    private Category category;
    private Restaurant restaurant;
    private DomainException domainException;

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
                ,10.0, restaurant,"www.pizza.com",StatusDish.INA);
        dishUpadated = new Dish(1L,null, null, null
                ,null, null,null,StatusDish.ACT);
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
        domainException = assertThrows(DomainException.class,() -> useCaseDish.saveDish(newDish));
        assertEquals(ConstantsErrorMessage.PRICE_MUST_BE_GREATER_THAN, domainException.getMessage());
        verify(iDishPersistencePort,never()).save(any(Dish.class));
    }

    @Test
    void test_create_dish_without_name(){
        newDish.setNameDish(null);
        domainException = assertThrows(DomainException.class,() -> useCaseDish.saveDish(newDish));
        assertEquals(ConstantsErrorMessage.DISH_NAME_CANT_BE_NULL, domainException.getMessage());
        verify(iDishPersistencePort,never()).save(any(Dish.class));
    }

    @Test
    void test_create_dish_without_description(){
        newDish.setDishDescription(null);
        domainException = assertThrows(DomainException.class,() -> useCaseDish.saveDish(newDish));
        assertEquals(ConstantsErrorMessage.DISH_DESCRIPTION_CANT_BE_NULL, domainException.getMessage());
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
        domainException = assertThrows(DomainException.class, ()-> useCaseDish.updateDish(user,newDish));
        assertEquals(ConstantsErrorMessage.DISH_NOT_FOUND, domainException.getMessage());
        verify(iDishPersistencePort,never()).save(any(Dish.class));
    }

    @Test
    void test_update_dish_non_owner_restaurant_dish(){
        newDish.getRestaurant().setIdOwner(10L);
        domainException = assertThrows(DomainException.class, () -> useCaseDish.updateDish(user,newDish));
        assertEquals(ConstantsErrorMessage.INCORRECT_OWNER_TO_UPDATE, domainException.getMessage());
        verify(iDishPersistencePort,never()).save(any(Dish.class));
    }

    /** Start test to update status dish */
    @Test
    void test_update_status_dish_active(){
        useCaseDish.updateDishStatus(user,dishUpadated);
        verify(iDishPersistencePort,times(1)).save(argThat(updatedDish ->
                updatedDish.getIdDish().equals(dishUpadated.getIdDish()) &&
                updatedDish.getStatusDish().equals(StatusDish.ACT)
        ));
    }

    @Test
    void test_update_status_dish_inactive(){
        newDish.setStatusDish(StatusDish.ACT);
        dishUpadated.setStatusDish(StatusDish.INA);
        useCaseDish.updateDishStatus(user,dishUpadated);
        verify(iDishPersistencePort,times(1)).save(argThat(updatedDish ->
                updatedDish.getIdDish().equals(dishUpadated.getIdDish()) &&
                updatedDish.getStatusDish().equals(StatusDish.INA)
        ));
    }

    /** List dish with pagination and filter by category */
    @Test
    void test_get_dish_list_by_restaurant_and_category() {
    Long idRestaurant = 1L;
        int page = 0;
        int size = 10;
        Long idCategory = 1L;


        Dish dishOne = new Dish(1L, "Pizza", category, "Delicious pizza", 12.99, restaurant, "http://example.com/pizza.jpg", StatusDish.ACT);
        Dish dishTwo = new Dish(2L, "Burger", category, "Juicy burger", 8.99, restaurant, "http://example.com/burger.jpg", StatusDish.ACT);
        Dish dishThree = new Dish(3L, "Pasta", category, "Creamy pasta", 10.99, restaurant, "http://example.com/pasta.jpg", StatusDish.ACT);

        List<Dish> dishList = List.of(dishOne, dishTwo, dishThree);

        when(iDishPersistencePort.findAllByRestaurantIdAndCategoryId(eq(idRestaurant), eq(idCategory), any()))
                .thenReturn(dishList);

        List<Dish> result = useCaseDish.getDishList(idRestaurant, page, size, idCategory);

        assertEquals(dishList, result);
        verify(iDishPersistencePort, times(1))
                .findAllByRestaurantIdAndCategoryId(eq(idRestaurant), eq(idCategory), any());
    }
}
