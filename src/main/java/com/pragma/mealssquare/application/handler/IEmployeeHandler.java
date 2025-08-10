package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.UserEmployeeDTORequest;

public interface IEmployeeHandler {
    void saveEmployee(UserEmployeeDTORequest userEmployeeDTORequest, String email);
}
