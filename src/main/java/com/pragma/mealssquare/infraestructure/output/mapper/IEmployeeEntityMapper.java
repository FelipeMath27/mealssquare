package com.pragma.mealssquare.infraestructure.output.mapper;

import com.pragma.mealssquare.domain.model.Employee;
import com.pragma.mealssquare.domain.model.Restaurant;

import com.pragma.mealssquare.infraestructure.output.entity.EmployeeEntity;
import com.pragma.mealssquare.infraestructure.output.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeEntityMapper {

    @Mapping(source = "restaurant.idRestaurant", target = "restaurantEntity.idRestaurant")
    EmployeeEntity toEmployeeEntity(Employee employee);

    @Mapping(source = "restaurantEntity", target = "restaurant")
    Employee toEmployee(EmployeeEntity employeeEntity);

    default Restaurant mapRestaurantEntityToRestaurant(RestaurantEntity restaurantEntity){
        if (restaurantEntity == null){
            return null;
        }
        return new Restaurant(restaurantEntity.getIdRestaurant(), restaurantEntity.getNameRestaurant(), restaurantEntity.getAddressRestaurant(),
                restaurantEntity.getIdOwner(), restaurantEntity.getPhoneNumberRestaurant(),restaurantEntity.getUrlLogo(),restaurantEntity.getNit());
    }
}
