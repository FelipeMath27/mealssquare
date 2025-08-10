package com.pragma.mealssquare.domain.api;


import com.pragma.mealssquare.domain.model.Employee;
import com.pragma.mealssquare.domain.model.User;

public interface IEmployeeServicePort {
    void saveEmployee(Employee employee, User user, User userOwner);
}
