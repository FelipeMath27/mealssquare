package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.EmployeeRestaurant;

public interface IEmployeeRestaurantPersistencePort {
    void saveNewEmployee(EmployeeRestaurant employeeRestaurant);
}
