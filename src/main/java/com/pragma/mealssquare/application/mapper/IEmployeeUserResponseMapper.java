package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.UserEmployeeDTOResponse;
import com.pragma.mealssquare.domain.model.Employee;
import com.pragma.mealssquare.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = RestaurantRequestMapper.class)
public interface IEmployeeUserResponseMapper {

    @Mapping(source = "user", target = "userDTOResponse")
    @Mapping(source = "employee", target = "employeeDTOResponse")
    UserEmployeeDTOResponse toResponse(User user, Employee employee);
}

