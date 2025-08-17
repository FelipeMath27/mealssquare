package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.OrderDTOResponse;
import com.pragma.mealssquare.domain.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {RestaurantResponseMapper.class,IUserResponseMapper.class})
public interface IOrderResponseMapper {
    OrderDTOResponse toResponse(Order order);
}
