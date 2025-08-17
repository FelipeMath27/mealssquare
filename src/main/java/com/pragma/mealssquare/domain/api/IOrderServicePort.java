package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Order;

public interface IOrderServicePort {
    Order saveOrder(Order order, Long idUser);
}
