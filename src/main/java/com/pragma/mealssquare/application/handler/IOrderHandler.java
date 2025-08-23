package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.OrderDTORequest;
import com.pragma.mealssquare.application.dto.OrderDTOResponse;
import com.pragma.mealssquare.application.dto.PageDTOResponse;
import com.pragma.mealssquare.domain.model.StatusOrder;

public interface IOrderHandler {
    OrderDTOResponse saveOrder(OrderDTORequest orderDTORequest);

    PageDTOResponse<OrderDTOResponse> getAllOrders(int page, int size, StatusOrder statusOrder, String email);
}
