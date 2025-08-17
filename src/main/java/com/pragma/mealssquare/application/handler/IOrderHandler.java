package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.OrderDTORequest;
import com.pragma.mealssquare.application.dto.OrderDTOResponse;

public interface IOrderHandler {
    OrderDTOResponse saveOrder(OrderDTORequest orderDTORequest);
}
