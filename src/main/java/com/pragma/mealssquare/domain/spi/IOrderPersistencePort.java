package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);

    List<Order> findAllByIdClient(Long idClient);
}