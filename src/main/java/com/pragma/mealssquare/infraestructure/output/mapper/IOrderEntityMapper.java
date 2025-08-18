package com.pragma.mealssquare.infraestructure.output.mapper;

import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.infraestructure.output.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {IOrderDetailEntityMapper.class})
public interface IOrderEntityMapper {

    @Mapping(source = "restaurant.idRestaurant", target = "restaurantEntity.idRestaurant")
    OrderEntity toOrderEntity(Order order);

    @Mapping(source = "restaurantEntity.idRestaurant", target = "restaurant.idRestaurant")
    Order toOrder(OrderEntity orderEntity);

    List<Order> toOrderList(List<OrderEntity> orderEntities);

    List<OrderEntity> toOrderEntityList(List<Order> orders);

}
