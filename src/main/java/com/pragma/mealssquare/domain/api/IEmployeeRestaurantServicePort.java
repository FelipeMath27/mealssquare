package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.EmployeeRestaurant;
import com.pragma.mealssquare.domain.model.User;

public interface IEmployeeRestaurantServicePort {
    void saveEmployee(User user, EmployeeRestaurant employeeRestaurant);
}
