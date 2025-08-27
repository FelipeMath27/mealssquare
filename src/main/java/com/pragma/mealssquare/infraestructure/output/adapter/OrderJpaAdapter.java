package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.domain.model.StatusOrder;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.spi.IOrderPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import com.pragma.mealssquare.infraestructure.output.entity.OrderEntity;
import com.pragma.mealssquare.infraestructure.output.mapper.IOrderEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IOrderRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository iOrderRepository;
    private final IOrderEntityMapper iOrderEntityMapper;

    @Override
    public Order saveOrder(Order order) {
        try {
            OrderEntity orderEntity = iOrderEntityMapper.toOrderEntity(order);
            orderEntity.getOrderDetailEntityList().forEach(orderDetailEntity -> orderDetailEntity.setOrderEntity(orderEntity));
            return iOrderEntityMapper.toOrder(iOrderRepository.save(orderEntity));
        } catch (DataAccessException | PersistenceException ex) {
            throw new CustomException(ConstantsErrorMessage.CANT_SAVE_ORDER + " " + ex.getMessage());
        }
    }

    @Override
    public List<Order> findAllByIdUser(Long idClient) {
        return iOrderEntityMapper.toOrderList(iOrderRepository.findAllByIdClient(idClient));
    }

    @Override
    public PageResult<Order> findAllOrdersByIdRestaurant(Long idRestaurant, StatusOrder statusOrder, Pagination pagination) {
        Pageable pageable = PageRequest.of(
                pagination.page(),
                pagination.size()
        );

        Page<OrderEntity> page = iOrderRepository.findAllByRestaurantEntity_IdRestaurantAndStatusOrder(
                idRestaurant,
                statusOrder.name(),
                pageable
        );

        List<Order> orders = iOrderEntityMapper.toOrderList(page.getContent());

        return new PageResult<>(
                orders,
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public Optional<Order> findById(Long idOrder) {
        return Optional.ofNullable(iOrderEntityMapper.toOrder(iOrderRepository.findById(idOrder).orElseThrow(() -> new CustomException(ConstantsErrorMessage.ORDER_NOT_FOUND))));
    }
}
