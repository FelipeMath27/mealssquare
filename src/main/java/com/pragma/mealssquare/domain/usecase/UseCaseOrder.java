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
import com.pragma.mealssquare.domain.validator.EmployeeOrder;
import com.pragma.mealssquare.domain.validator.StatusOrderValidators;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
public class UseCaseOrder implements IOrderServicePort {
    private final IRestaurantPersistencePort iRestaurantPersistencePort;
    private final IOrderPersistencePort iOrderPersistencePort;
    private final IDishPersistencePort iDishPersistencePort;
    private final IEmployeePersistencePort iEmployeePersistencePort;
    private static final StatusOrderValidators statusOrderValidators = new StatusOrderValidators();

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

    @Override
    public Order updateOrderAssign(Long idOrder, Long idEmployee) {
        EmployeeOrder validateEmployeeAndOrder = validateEmployeeAndOrder(idEmployee, idOrder);
        final Employee employee = validateEmployeeAndOrder.employee();
        final Order order = validateEmployeeAndOrder.order();

        if (order.getStatusOrder() != StatusOrder.PENDING) {
            throw new DomainException(ConstantsErrorMessage.ORDER_CANNOT_BE_ASSIGNED);
        }

        order.setIdEmployee(employee.getIdEmployee());

        if(!Objects.equals(order.getIdEmployee(), employee.getIdEmployee())){
            throw new DomainException(ConstantsErrorMessage.UNAUTHORIZED_OPERATION);
        }

        order.setStatusOrder(StatusOrder.IN_PROGRESS);

        return iOrderPersistencePort.saveOrder(order);
    }

    @Override
    public Order updateStatusOrder(Long idOrder, StatusOrder statusOrder, Long idEmployee, String pin, Optional<String> responsePinOpt) {
        EmployeeOrder validateEmployeeAndOrder = validateEmployeeAndOrder(idEmployee, idOrder);
        final Order order = validateEmployeeAndOrder.order();
        final Employee employee = validateEmployeeAndOrder.employee();

        if(!Objects.equals(order.getIdEmployee(), employee.getIdEmployee())){
            throw new DomainException(ConstantsErrorMessage.UNAUTHORIZED_OPERATION);
        }

        StatusOrder previousStatus = order.getStatusOrder();
        if (!statusOrderValidators.isValidTransition(previousStatus, statusOrder)) {
            throw new DomainException(ConstantsErrorMessage.INVALID_ORDER_TRANSITION);
        }
        if (StatusOrder.DELIVERED.equals(statusOrder)){
            String responsePin = responsePinOpt.orElseThrow(() -> new DomainException(ConstantsErrorMessage.PIN_REQUIRED));
            if(!Objects.equals(pin, responsePin)){
                throw new DomainException(ConstantsErrorMessage.INVALID_PIN);
            }
        }
        order.setStatusOrder(statusOrder);
        return iOrderPersistencePort.saveOrder(order);
    }

    @Override
    public Order getOrderById(Long idOrder) {
        return iOrderPersistencePort.findById(idOrder).
                orElseThrow(()-> new DomainException(ConstantsErrorMessage.ORDER_NOT_FOUND));
    }

    private EmployeeOrder validateEmployeeAndOrder(Long idEmployee, Long idOrder) {
        Employee employee = iEmployeePersistencePort.findById(idEmployee)
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.EMPLOYEE_NOT_FOUND));
        Order order = iOrderPersistencePort.findById(idOrder)
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.ORDER_NOT_FOUND));
        if (!Objects.equals(order.getRestaurant().getIdRestaurant(), employee.getRestaurant().getIdRestaurant())) {
            throw new DomainException(ConstantsErrorMessage.ORDER_NOT_BELONG_TO_EMPLOYEE_RESTAURANT);
        }
        return new EmployeeOrder(employee, order);
    }
}
