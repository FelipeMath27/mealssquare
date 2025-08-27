package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.domain.model.StatusOrder;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;

public interface IOrderServicePort {
    Order saveOrder(Order order, Long idUser);

    PageResult<Order> getOrderListByStatus(Long idEmployee, StatusOrder statusOrder, Pagination pagination);

    Order updateOrderassign(Long idOrder, Long idEmployee);
}
