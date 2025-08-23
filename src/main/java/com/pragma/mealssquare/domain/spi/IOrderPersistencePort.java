package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.domain.model.StatusOrder;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;

import java.util.List;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);

    List<Order> findAllByIdUser(Long idClient);

    PageResult<Order> findAllOrdersByIdRestaurant(Long idRestaurant, StatusOrder statusOrder, Pagination pagination);
}