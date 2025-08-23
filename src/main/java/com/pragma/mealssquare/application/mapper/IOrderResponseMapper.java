package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.OrderDTOResponse;
import com.pragma.mealssquare.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {RestaurantResponseMapper.class,IUserResponseMapper.class, IOrderDetailResponseMapper.class})
public interface IOrderResponseMapper {
    @Mapping(source = "restaurant", target = "restaurantDTOResponse")
    @Mapping(source = "orderDetailList", target = "orderDetailList")
    OrderDTOResponse toResponse(Order order);

    List<OrderDTOResponse> toOrderDtoList(List<Order> orderList);
}
