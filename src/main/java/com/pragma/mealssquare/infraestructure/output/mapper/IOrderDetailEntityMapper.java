package com.pragma.mealssquare.infraestructure.output.mapper;

import com.pragma.mealssquare.domain.model.OrderDetail;
import com.pragma.mealssquare.infraestructure.output.entity.OrderDetailEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface IOrderDetailEntityMapper {

    @Mapping(source = "dish.idDish", target = "dishEntity.idDish")
    @Mapping(source = "order.idOrder", target = "orderEntity.idOrder")
    OrderDetailEntity toOrderDetailEntity(OrderDetail orderDetail);

    @Mapping(source = "dishEntity.idDish", target = "dish.idDish")
    @Mapping(source = "orderEntity.idOrder", target = "order.idOrder")
    OrderDetail toOrderDetail(OrderDetailEntity orderDetailEntity);
}
