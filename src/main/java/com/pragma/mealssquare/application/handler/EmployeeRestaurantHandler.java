package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.UserEmployeeDTORequest;
import com.pragma.mealssquare.application.mapper.IEmployeeRestaurantRequestMapper;
import com.pragma.mealssquare.domain.api.IEmployeeRestaurantServicePort;
import com.pragma.mealssquare.domain.model.EmployeeRestaurant;
import com.pragma.mealssquare.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeRestaurantHandler implements IEmployeeRestaurantHandler{
    private final IEmployeeRestaurantServicePort iEmployeeRestaurantServicePort;
    private final IEmployeeRestaurantRequestMapper iEmployeeRestaurantRequestMapper;

    @Override
    public void saveEmployee(UserEmployeeDTORequest userEmployeeDTORequest) {
        User user = iEmployeeRestaurantRequestMapper.employeeToUser(userEmployeeDTORequest.getUserDTORequest());
        EmployeeRestaurant employeeRestaurant = iEmployeeRestaurantRequestMapper.toEmployeeRestaurant(userEmployeeDTORequest.getEmployeeDTORequest());
        iEmployeeRestaurantServicePort.saveEmployee(user,employeeRestaurant);
    }
}
