package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.EmployeeDTORequest;
import com.pragma.mealssquare.application.dto.UserDTORequest;
import com.pragma.mealssquare.domain.model.Employee;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {RestaurantRequestMapper.class})
public interface IEmployeeUserRequestMapper {
    User toUser(UserDTORequest userDTORequest);


    @Mapping(target = "restaurant", expression = "java(createRestaurantById(employeeDTORequest.getIdRestaurant()))")
    Employee toEmployee(EmployeeDTORequest employeeDTORequest);

    default Restaurant createRestaurantById(Long idRestaurant) {
        if (idRestaurant == null) {
            return null;
        }
        return new Restaurant(idRestaurant, null, null, null, null, null, null);
    }

}
