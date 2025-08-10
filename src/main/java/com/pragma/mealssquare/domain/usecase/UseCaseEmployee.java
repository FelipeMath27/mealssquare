package com.pragma.mealssquare.domain.usecase;

import com.pragma.mealssquare.domain.api.IEmployeeServicePort;
import com.pragma.mealssquare.domain.model.Employee;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.StatusEmployee;
import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.spi.IEmployeePersistencePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.domain.validator.ValidatorClasses;
import com.pragma.mealssquare.domain.validator.ValidatorService;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
public class UseCaseEmployee implements IEmployeeServicePort {
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final ValidatorService validatorService;
    private final IEmployeePersistencePort iEmployeePersistencePort;

    @Override
    public void saveEmployee(Employee employee, User user, User userOwner) {
        log.info(ConstantsErrorMessage.START_FLOW_TO_CREATE_EMPLOYEE);
        ValidatorClasses.validateNotNullId(user.getIdUser(),ConstantsErrorMessage.USER_ID_NOT_FOUND);
        Optional<Restaurant> restaurant = Optional.ofNullable(iRestaurantPersistencePort.findRestaurantById(employee.getRestaurant().getIdRestaurant())
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.RESTAURANT_NOT_FOUND)));
        validateOwnerCreatingEmployee(restaurant,userOwner);
        validatorService.validateRestaurantExist(employee.getRestaurant());
        processToCreateEmployee(employee,user);
    }

    private void processToCreateEmployee(Employee employee, User user) {
        log.info(ConstantsErrorMessage.START_PROCESS_TO_VALIDATE_CONDITION);
        Employee newEmployee = Employee.builder()
                .idUser(user.getIdUser())
                .restaurant(employee.getRestaurant())
                .typePositionEmployee(employee.getTypePositionEmployee())
                .statusEmployee(StatusEmployee.ACT)
                .entryDate(LocalDate.now())
                .build();

        iEmployeePersistencePort.save(newEmployee);
    }

    private void validateOwnerCreatingEmployee(Optional<Restaurant> restaurantOpt, User userOwner) {
        restaurantOpt
                .filter(restaurant -> userOwner.getIdUser().equals(restaurant.getIdOwner()))
                .orElseThrow(() -> new CustomException(ConstantsErrorMessage.INCORRECT_OWNER_TO_CREATE_EMPLOYEE));
    }


}
