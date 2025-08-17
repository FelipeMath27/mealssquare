package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.OrderDTORequest;
import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {RestaurantRequestMapper.class})
public interface IOrderRequestMapper {

    @Mapping(target = "restaurant", expression = "java(createRestaurantById(orderDTORequest.getIdRestaurant()))")
    Order toOrder(OrderDTORequest orderDTORequest);

    default Restaurant createRestaurantById(Long idRestaurant) {
        if (idRestaurant == null) {
            return null;
        }
        return new Restaurant(idRestaurant, null, null, null, null, null, null);
    }

}
