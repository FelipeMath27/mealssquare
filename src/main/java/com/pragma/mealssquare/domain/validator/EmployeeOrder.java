package com.pragma.mealssquare.domain.validator;

import com.pragma.mealssquare.domain.model.Employee;
import com.pragma.mealssquare.domain.model.Order;

public record EmployeeOrder(Employee employee,Order order) {
}
