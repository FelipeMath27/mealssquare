package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.OrderDTORequest;
import com.pragma.mealssquare.application.dto.OrderDTOResponse;
import com.pragma.mealssquare.application.dto.PageDTOResponse;
import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.application.mapper.IOrderDetailRequestMapper;
import com.pragma.mealssquare.application.mapper.IOrderRequestMapper;
import com.pragma.mealssquare.application.mapper.IOrderResponseMapper;
import com.pragma.mealssquare.application.mapper.RestaurantResponseMapper;
import com.pragma.mealssquare.domain.api.IOrderServicePort;
import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.domain.model.OrderDetail;
import com.pragma.mealssquare.domain.model.StatusOrder;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderHandler implements IOrderHandler{
    private final IUserFeignHandler iUserFeignHandler;
    private final IOrderServicePort iOrderServicePort;
    private final IOrderRequestMapper iOrderRequestMapper;
    private final IOrderResponseMapper iOrderResponseMapper;
    private final IOrderDetailRequestMapper iOrderDetailRequestMapper;
    private final RestaurantResponseMapper restaurantResponseMapper;
    private UserDTOResponse userDTOResponse;

    @Override
    public OrderDTOResponse saveOrder(OrderDTORequest orderDTORequest) {
        try {
            Order order = iOrderRequestMapper.toOrder(orderDTORequest);
            if (orderDTORequest.getIdClient() == null) {
                throw new DomainException(ConstantsErrorMessage.CANT_BE_NULL);
            }
            userDTOResponse = iUserFeignHandler.getUserById(orderDTORequest.getIdClient());
            List<OrderDetail> orderDetailList = orderDTORequest.getOrderDetailList()
                    .stream()
                    .map(iOrderDetailRequestMapper::toOrderDetail)
                    .peek(orderDetail -> orderDetail.setOrder(order))
                    .toList();
            order.setOrderDetailList(orderDetailList);
            OrderDTOResponse orderDTOResponse = iOrderResponseMapper.toResponse(iOrderServicePort.saveOrder(order, userDTOResponse.getIdUser()));
            orderDTOResponse.setClientDTOResponse(userDTOResponse);
            return orderDTOResponse;
        } catch (UsernameNotFoundException ex){
            throw new InfrastructureException(ConstantsErrorMessage.USER_NOT_FOUD,ex);
        }
    }

    @Override
    public PageDTOResponse<OrderDTOResponse> getAllOrders(int page, int size, StatusOrder statusOrder, String email) {
        try {
            Pagination pagination = new Pagination(page,size);
            userDTOResponse = iUserFeignHandler.getUserByEmail(email);
            Long idEmployee = userDTOResponse.getIdUser();
            PageResult<Order> pageResult = iOrderServicePort.getOrderListByStatus(idEmployee,statusOrder,pagination);
            return new PageDTOResponse<>(
                    iOrderResponseMapper.toOrderDtoList(pageResult.content()),
                    page,
                    size,
                    pageResult.totalPages(),
                    pageResult.totalElements()
            );
        } catch (UsernameNotFoundException ex){
            throw new DomainException(ConstantsErrorMessage.USER_NOT_FOUD);
        }
    }
}
