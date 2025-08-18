package com.pragma.mealssquare.application.mapper;


import com.pragma.mealssquare.application.dto.OrderDetailDTOResponse;
import com.pragma.mealssquare.domain.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {IDishResponseMapper.class})
public interface IOrderDetailResponseMapper {

    @Mapping(source = "dish", target = "dishDTOResponse")
    OrderDetailDTOResponse toResponse(OrderDetail orderDetail);

    List<OrderDetailDTOResponse> toResponseList(List<OrderDetail> orderDetails);
}
