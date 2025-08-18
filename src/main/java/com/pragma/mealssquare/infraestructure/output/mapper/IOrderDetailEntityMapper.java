package com.pragma.mealssquare.infraestructure.output.mapper;

import com.pragma.mealssquare.domain.model.OrderDetail;
import com.pragma.mealssquare.infraestructure.output.entity.OrderDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface IOrderDetailEntityMapper {

    @Mapping(source = "dish", target = "dishEntity")
    @Mapping(source = "order", target = "orderEntity")
    OrderDetailEntity toOrderDetailEntity(OrderDetail orderDetail);

    @Mapping(source = "dishEntity", target = "dish")
    @Mapping(source = "orderEntity", target = "order")
    OrderDetail toOrderDetail(OrderDetailEntity orderDetailEntity);
}
