package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.application.dto.OrderDetailDTOResponse;
import com.pragma.mealssquare.domain.model.OrderDetail;

public interface IOrderDetailPersistencePort {
    OrderDetail saveOrderDetail(OrderDetail orderDetail);
    OrderDetail findOrderDetailById(Long idOrderDetail);
}
