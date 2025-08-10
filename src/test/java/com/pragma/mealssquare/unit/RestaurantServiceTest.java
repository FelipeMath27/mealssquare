package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.application.handler.IUserFeignHandler;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseRestaurant;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.domain.validator.ValidatorService;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.mockito.Mockito.*;

    class RestaurantServiceTest {

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @Mock
    private IUserFeignHandler iUserFeignHandler;

    @Mock
    private IUserResponseMapper iUserResponseMapper;

    @Mock
    private ValidatorService validatorService;

    @InjectMocks
    private UseCaseRestaurant useCaseRestaurant;

    private User creatorUser, ownerUser;
    private Restaurant restaurant;
    private Rol adminRole,ownerRole;

    private CustomException customException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        adminRole = new Rol(1L, TypeRolEnum.ADMIN.name(), "Admin rol");
        ownerRole = new Rol(2L, TypeRolEnum.OWNER.name(), "Rol owner");

        creatorUser = new User(1L, "felipe", "mesa", TypeDocumentEnum.CC, "1015141",
                "+57321321", LocalDate.of(1994, 5, 27), "felipe@gmail.com",
                "231321", adminRole);
        ownerUser = new User(2L, "Tania", "mesa", TypeDocumentEnum.CC, "1019",
                "+57323231", LocalDate.of(1992, 9, 8), "tania@gmail.com",
                "0000", ownerRole);

        restaurant = new Restaurant(1L, "RestaurantName", "RestaurantAddress",
                ownerUser.getIdUser(), "+573142212051", "www.uno.com", "123456789");
        when(iUserFeignHandler.getUserByEmail(ownerUser.getEmail())).thenReturn(new UserDTOResponse());
        when(iUserFeignHandler.getUserById(ownerUser.getIdUser())).thenReturn(new UserDTOResponse());
        when(iUserResponseMapper.toUser(any(UserDTOResponse.class))).thenReturn(ownerUser);
        doNothing().when(validatorService).validateRestaurantExist(any(Restaurant.class));
    }

    @Test
    void test_create_restaurant(){
        useCaseRestaurant.saveRestaurants(restaurant,ownerUser);
        verify(iRestaurantPersistencePort,times(1)).save(any(Restaurant.class));
    }

    @Test
    void test_createRestaurant_with_non_user_owner(){
    restaurant.setIdOwner(adminRole.getIdRol());
    when(iUserFeignHandler.getUserById(creatorUser.getIdUser())).thenReturn(new UserDTOResponse());
    customException = assertThrows(CustomException.class,() -> useCaseRestaurant.saveRestaurants(restaurant,creatorUser));
    assertEquals(ConstantsErrorMessage.IS_NOT_OWNER_ROLE,customException.getMessage());
    verify(iRestaurantPersistencePort,never()).save(any(Restaurant.class));
    }

    @Test
    void test_createRestaurant_with_invalid_Name_Restaurant(){
        restaurant.setNameRestaurant("123456");
        customException = assertThrows(CustomException.class, () -> useCaseRestaurant.saveRestaurants(restaurant,ownerUser));
        assertEquals(ConstantsErrorMessage.INVALID_RESTAURANT_NAME_FORMAT,customException.getMessage());
        verify(iRestaurantPersistencePort,never()).save(any(Restaurant.class));
    }

    @Test
        void test_createRestaurant_with_invalid_number_Restaurant(){
        restaurant.setPhoneNumberRestaurant("+071234561212");
        customException = assertThrows(CustomException.class, () -> useCaseRestaurant.saveRestaurants(restaurant,ownerUser));
        assertEquals(ConstantsErrorMessage.INVALID_FORMAT_PHONE, customException.getMessage());
        verify(iRestaurantPersistencePort,never()).save(any(Restaurant.class));
    }

    @Test
    void test_createRestaurant_with_invalid_nit(){
        restaurant.setNit("ABVCSACAC1");
        customException = assertThrows(CustomException.class, () -> useCaseRestaurant.saveRestaurants(restaurant,ownerUser));
        assertEquals(ConstantsErrorMessage.INVALID_FORMAT_NIT, customException.getMessage());
        verify(iRestaurantPersistencePort,never()).save(any(Restaurant.class));
    }
}
