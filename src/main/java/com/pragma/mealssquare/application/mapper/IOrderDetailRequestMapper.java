package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.OrderDetailDTORequest;
import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {IDishRequestMapper.class})
public interface IOrderDetailRequestMapper {

    @Mapping(target = "dish", expression = "java(createDishById(orderDetailDTORequest.getIdDish()))")
    OrderDetail toOrderDetail(OrderDetailDTORequest orderDetailDTORequest);

    default Dish createDishById(Long idDish) {
        if (idDish == null) {
            return null;
        }
        return new Dish(idDish, null, null, null, null, null, null, null);
    }

}
