package com.pragma.mealssquare.domain.usecase;

import com.pragma.mealssquare.domain.api.IOrderServicePort;
import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.StatusOrder;
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

    @Override
    public Order saveOrder(Order order, Long idUser) {
        log.info(ConstantsErrorMessage.START_FLOW);
        List<Order> listExistingOrders = iOrderPersistencePort.findAllByIdClient(idUser);
        boolean hasPendingOrders = listExistingOrders.stream()
                .anyMatch(existingOrder -> existingOrder.getStatusOrder() == StatusOrder.PENDING
                        || existingOrder.getStatusOrder() == StatusOrder.IN_PROGRESS
                        || existingOrder.getStatusOrder() == StatusOrder.DISH_READY);
        if (hasPendingOrders) throw new DomainException(ConstantsErrorMessage.CLIENT_WITH_ORDERS);
        Restaurant restaurant = iRestaurantPersistencePort.findRestaurantById(order.getRestaurant().getIdRestaurant())
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.RESTAURANT_NOT_FOUND));
        order.setDateOrder(LocalDate.now());
        order.setRestaurant(restaurant);
        order.setIdClient(idUser);
        order.setStatusOrder(StatusOrder.PENDING);

        return iOrderPersistencePort.saveOrder(order);
    }
}
