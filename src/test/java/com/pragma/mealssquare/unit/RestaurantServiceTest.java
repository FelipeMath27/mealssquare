package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.application.handler.IUserFeignHandler;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseRestaurant;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.domain.validator.ValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
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

    private DomainException domainException;

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
        domainException = assertThrows(DomainException.class,() -> useCaseRestaurant.saveRestaurants(restaurant,creatorUser));
    assertEquals(ConstantsErrorMessage.IS_NOT_OWNER_ROLE,domainException.getMessage());
    verify(iRestaurantPersistencePort,never()).save(any(Restaurant.class));
    }

    @Test
    void test_createRestaurant_with_invalid_Name_Restaurant(){
        restaurant.setNameRestaurant("123456");
        domainException = assertThrows(DomainException.class, () -> useCaseRestaurant.saveRestaurants(restaurant,ownerUser));
        assertEquals(ConstantsErrorMessage.INVALID_RESTAURANT_NAME_FORMAT,domainException.getMessage());
        verify(iRestaurantPersistencePort,never()).save(any(Restaurant.class));
    }

    @Test
        void test_createRestaurant_with_invalid_number_Restaurant(){
        restaurant.setPhoneNumberRestaurant("+071234561212");
        domainException = assertThrows(DomainException.class, () -> useCaseRestaurant.saveRestaurants(restaurant,ownerUser));
        assertEquals(ConstantsErrorMessage.INVALID_FORMAT_PHONE, domainException.getMessage());
        verify(iRestaurantPersistencePort,never()).save(any(Restaurant.class));
    }

    @Test
    void test_createRestaurant_with_invalid_nit(){
        restaurant.setNit("ABVCSACAC1");
        domainException = assertThrows(DomainException.class, () -> useCaseRestaurant.saveRestaurants(restaurant,ownerUser));
        assertEquals(ConstantsErrorMessage.INVALID_FORMAT_NIT, domainException.getMessage());
        verify(iRestaurantPersistencePort,never()).save(any(Restaurant.class));
    }

    @Test
    void test_consult_list_restaurant_with_pagination_10_per_page( ) {
        Restaurant restaurant1 = new Restaurant(1L, "R1", "Address1",
                ownerUser.getIdUser(), "+573142212051", "www.r1.com", "111111111");
        Restaurant restaurant2 = new Restaurant(2L, "R2", "Address2",
                ownerUser.getIdUser(), "+573142212052", "www.r2.com", "222222222");

        List<Restaurant> restaurantList = List.of(restaurant1, restaurant2);

        Pagination pagination = new Pagination(0, 10);

        PageResult<Restaurant> pageResult = new PageResult<>(
                restaurantList,
                1,
                2
        );

        when(iRestaurantPersistencePort.getAllRestaurants(pagination))
                .thenReturn(pageResult);

        PageResult<Restaurant> result = useCaseRestaurant.getAllRestaurants(pagination);

        assertNotNull(result);
        assertEquals(2, result.content().size());
        assertEquals("R1", result.content().get(0).getNameRestaurant());
        assertEquals("R2", result.content().get(1).getNameRestaurant());

        verify(iRestaurantPersistencePort, times(1)).getAllRestaurants(pagination);
    }
}
