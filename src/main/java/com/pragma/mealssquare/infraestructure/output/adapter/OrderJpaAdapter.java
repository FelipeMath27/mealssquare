package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderJpaAdapter implements IOrderPersistencePort {

    @Override
    public Order saveOrder(Order order) {
        return null;
    }
}
