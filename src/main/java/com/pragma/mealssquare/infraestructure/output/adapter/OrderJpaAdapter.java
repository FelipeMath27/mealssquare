package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.domain.model.StatusOrder;
import com.pragma.mealssquare.domain.spi.IOrderPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import com.pragma.mealssquare.infraestructure.output.entity.OrderEntity;
import com.pragma.mealssquare.infraestructure.output.mapper.IOrderEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IOrderRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository iOrderRepository;
    private final IOrderEntityMapper iOrderEntityMapper;

    @Override
    public Order saveOrder(Order order) {
        try {
            return iOrderEntityMapper.toOrder(iOrderRepository.save(iOrderEntityMapper.toOrderEntity(order)));
        } catch (DataAccessException | PersistenceException ex) {
            throw new CustomException(ConstantsErrorMessage.CANT_SAVE_ORDER + " " + ex.getMessage());
        }
    }

    @Override
    public List<Order> findAllByIdClient(Long idClient) {
        return iOrderEntityMapper.toOrderList(iOrderRepository.findAllByIdClient(idClient));
    }
}
