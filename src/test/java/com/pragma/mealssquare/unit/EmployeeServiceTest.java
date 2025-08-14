package com.pragma.mealssquare.unit;

import com.pragma.mealssquare.application.handler.IUserFeignHandler;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.spi.IEmployeePersistencePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.usecase.UseCaseEmployee;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;


public class EmployeeServiceTest {

    @Mock
    private IUserFeignHandler iuserFeignHandler;

    @Mock
    private IUserResponseMapper iUserResponseMapper;

    @Mock
    private IEmployeePersistencePort iEmployeePersistencePort;

    @Mock
    private IRestaurantPersistencePort iRestaurantPersistencePort;

    @Mock
    private ValidatorService validatorService;

    @InjectMocks
    private UseCaseEmployee useCaseEmployee;

    private Employee employee;
    private User userEmployee, userOwner;
    private Rol rol, rolOwner;
    private Restaurant restaurant, restaurant1;
    private DomainException domainException;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        rol = new Rol(3L, "EMPLOYEE", "Rol employee");
        rolOwner = new Rol(2L, "OWNER", "Rol owner");
        userEmployee = new User(1L,"John Doe", "Clix", TypeDocumentEnum.CC,"95052701265",
                "+573203212051", LocalDate.of(1994,12,12),"felie@gmial.colm","123",rol);
        userOwner = new User(2L,"Owner","Restaurante",TypeDocumentEnum.CC,"123456789",
                "+573203212051", LocalDate.of(1990,1,1),"owner@gmail.co","123",rolOwner);
        restaurant = new Restaurant(1L, "RestaurantName", "RestaurantAddress",
                userOwner.getIdUser(), "+573142212051", "www.uno.com", "123456789");
        restaurant1 = new Restaurant(1L, null, null, null,null,null, null);
        employee = new Employee(null,null,restaurant1,null,TypePositionEmployee.COOK,null);
        doNothing().when(validatorService).validateRestaurantExist(any(Restaurant.class));
        when(iRestaurantPersistencePort.findRestaurantById(restaurant1.getIdRestaurant())).thenReturn(Optional.ofNullable(restaurant));
    }

    @Test
    void test_create_employee_success() {
        useCaseEmployee.saveEmployee(employee,userEmployee,userOwner);
        verify(iEmployeePersistencePort, times(1)).save(any(Employee.class));
    }

    @Test
    void test_create_employee_with_null_restaurant() {
        restaurant1.setIdRestaurant(2L);
         domainException = assertThrows(DomainException.class, () -> {
            useCaseEmployee.saveEmployee(employee, userEmployee, userOwner);
        });
        assertEquals(ConstantsErrorMessage.RESTAURANT_NOT_FOUND, domainException.getMessage());
    }

}
