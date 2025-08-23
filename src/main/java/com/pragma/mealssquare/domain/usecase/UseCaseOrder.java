package com.pragma.mealssquare.domain.usecase;

import com.pragma.mealssquare.domain.api.IOrderServicePort;
import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.*;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.spi.IDishPersistencePort;
import com.pragma.mealssquare.domain.spi.IEmployeePersistencePort;
import com.pragma.mealssquare.domain.spi.IOrderPersistencePort;
import com.pragma.mealssquare.domain.spi.IRestaurantPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
public class UseCaseOrder implements IOrderServicePort {
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IOrderPersistencePort iOrderPersistencePort;
    private final IDishPersistencePort iDishPersistencePort;
    private final IEmployeePersistencePort iEmployeePersistencePort;

    @Override
    public Order saveOrder(Order order, Long idUser) {
        log.info(ConstantsErrorMessage.START_FLOW);
        List<Order> listExistingOrders = iOrderPersistencePort.findAllByIdUser(idUser);
        boolean hasPendingOrders = listExistingOrders.stream()
                .anyMatch(existingOrder -> existingOrder.getStatusOrder() == StatusOrder.PENDING
                        || existingOrder.getStatusOrder() == StatusOrder.IN_PROGRESS
                        || existingOrder.getStatusOrder() == StatusOrder.DISH_READY);
        if (hasPendingOrders) throw new DomainException(ConstantsErrorMessage.CLIENT_WITH_ORDERS);
        log.info(ConstantsErrorMessage.START_CONSULT_LIST_RESTAURANT);
        Restaurant restaurant = iRestaurantPersistencePort.findRestaurantById(order.getRestaurant().getIdRestaurant())
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
        order.getOrderDetailList().forEach(orderDetail -> {
            Dish dish = iDishPersistencePort.findById(orderDetail.getDish().getIdDish())
                    .orElseThrow(() -> new DomainException(ConstantsErrorMessage.DISH_NOT_FOUND));
            orderDetail.setDish(dish);
            orderDetail.setOrder(order);
        });
        order.setDateOrder(LocalDate.now());
        order.setRestaurant(restaurant);
        order.setIdClient(idUser);
        order.setStatusOrder(StatusOrder.PENDING);
        return iOrderPersistencePort.saveOrder(order);
    }

    @Override
    public PageResult<Order> getOrderListByStatus(Long idEmployee, StatusOrder statusOrder, Pagination pagination) {
        Employee employee = iEmployeePersistencePort.findById(idEmployee).orElseThrow(() -> new DomainException(ConstantsErrorMessage.EMPLOYEE_NOT_FOUND));
        return iOrderPersistencePort.findAllOrdersByIdRestaurant(employee.getRestaurant().getIdRestaurant(), statusOrder, pagination);
    }
}
