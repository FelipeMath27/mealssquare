package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Order;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);
}
