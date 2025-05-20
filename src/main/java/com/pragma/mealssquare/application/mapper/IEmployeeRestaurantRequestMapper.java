package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.EmployeeDTORequest;
import com.pragma.mealssquare.application.dto.UserDTORequest;
import com.pragma.mealssquare.application.dto.UserEmployeeDTORequest;
import com.pragma.mealssquare.domain.model.EmployeeRestaurant;
import com.pragma.mealssquare.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeRestaurantRequestMapper {
    User employeeToUser(UserDTORequest userDTORequest);

    EmployeeRestaurant toEmployeeRestaurant (EmployeeDTORequest employeeDTORequest);

}
