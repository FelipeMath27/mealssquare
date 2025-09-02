package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.*;
import com.pragma.mealssquare.application.mapper.IOrderDetailRequestMapper;
import com.pragma.mealssquare.application.mapper.IOrderRequestMapper;
import com.pragma.mealssquare.application.mapper.IOrderResponseMapper;
import com.pragma.mealssquare.domain.api.IEmployeeServicePort;
import com.pragma.mealssquare.domain.api.IOrderServicePort;
import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.Employee;
import com.pragma.mealssquare.domain.model.Order;
import com.pragma.mealssquare.domain.model.OrderDetail;
import com.pragma.mealssquare.domain.model.StatusOrder;
import com.pragma.mealssquare.domain.pagination.PageResult;
import com.pragma.mealssquare.domain.pagination.Pagination;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    private final ITraceabilityFeignHandler iTraceabilityFeignHandler;
    private final INotificationFeignSMSHandler iNotificationFeignSMSHandler;
    private final IEmployeeServicePort iEmployeeServicePort;

    private UserDTOResponse employeeDTOResponse;
    private UserDTOResponse clientDTOResponse;
    private TraceabilityDTORequest traceabilityDTORequest;
    private NotificationDTOResponse notificationDTOResponse;
    private NotificationDTORequest notificationDTORequest;
    OrderDTOResponse orderDTOResponse;
    OrderDTOResponse updateOrderDTOResponse;

    @Override
    public OrderDTOResponse saveOrder(OrderDTORequest orderDTORequest) {
        try {
            Order order = iOrderRequestMapper.toOrder(orderDTORequest);
            if (orderDTORequest.getIdClient() == null) {
                throw new DomainException(ConstantsErrorMessage.CANT_BE_NULL);
            }
            employeeDTOResponse = iUserFeignHandler.getUserById(orderDTORequest.getIdClient());
            List<OrderDetail> orderDetailList = orderDTORequest.getOrderDetailList()
                    .stream()
                    .map(iOrderDetailRequestMapper::toOrderDetail)
                    .peek(orderDetail -> orderDetail.setOrder(order))
                    .toList();
            order.setOrderDetailList(orderDetailList);
            OrderDTOResponse orderDTOResponse = iOrderResponseMapper.toResponse(iOrderServicePort.saveOrder(order, employeeDTOResponse.getIdUser()));
            orderDTOResponse.setClientDTOResponse(employeeDTOResponse);
            return orderDTOResponse;
        } catch (UsernameNotFoundException ex){
            throw new DomainException(ConstantsErrorMessage.USER_NOT_FOUD + "{}" + ex);
        }
    }

    @Override
    public PageDTOResponse<OrderDTOResponse> getAllOrders(int page, int size, StatusOrder statusOrder, String email) {
        try {
            Pagination pagination = new Pagination(page,size);
            employeeDTOResponse = iUserFeignHandler.getUserByEmail(email);
            Long idEmployee = employeeDTOResponse.getIdUser();
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

    @Override
    public OrderDTOResponse  assignOrderToEmployee(Long idOrder, String email) {
        try {
            employeeDTOResponse = iUserFeignHandler.getUserByEmail(email);
            Long idEmployee = employeeDTOResponse.getIdUser();
            log.info(String.valueOf(employeeDTOResponse));
            Order order = iOrderServicePort.updateOrderAssign(idOrder,idEmployee);
            clientDTOResponse = iUserFeignHandler.getUserById(order.getIdClient());
            orderDTOResponse = iOrderResponseMapper.toResponse(order);
            orderDTOResponse.setClientDTOResponse(clientDTOResponse);
            orderDTOResponse.setEmployeeDTOResponse(employeeDTOResponse);
            iTraceabilityFeignHandler.writeTraceability(buildTraceabilityDTORequest(orderDTOResponse,employeeDTOResponse));
            return orderDTOResponse;
        } catch (UsernameNotFoundException ex){
            throw new DomainException(ConstantsErrorMessage.USER_NOT_FOUD + "{}" + ex);
        }
    }

    @Override
    public OrderDTOResponse updateStatusOrder(Long idOrder, StatusOrder statusOrder, String email, String pin) {
        try {
            Order orderUpdated;
            employeeDTOResponse = iUserFeignHandler.getUserByEmail(email);
            Order order = iOrderServicePort.getOrderById(idOrder);
            clientDTOResponse = iUserFeignHandler.getUserById(order.getIdClient());
            if (StatusOrder.DISH_READY.equals(statusOrder)){
                notificationDTORequest.setPhoneNumber(clientDTOResponse.getPhoneNumberUser());
                String responsePin = iNotificationFeignSMSHandler.sendSMS(notificationDTORequest).getPin();
                orderUpdated = iOrderServicePort.updateStatusOrder(order.getIdOrder(),statusOrder,employeeDTOResponse.getIdUser(),pin,Optional.of(responsePin));
            } else {
                orderUpdated = iOrderServicePort.updateStatusOrder(orderDTOResponse.getIdOrder(),
                        statusOrder,employeeDTOResponse.getIdUser(),pin, Optional.empty());
            }
            updateOrderDTOResponse = iOrderResponseMapper.toResponse(orderUpdated);
            updateOrderDTOResponse.setClientDTOResponse(clientDTOResponse);
            updateOrderDTOResponse.setEmployeeDTOResponse(employeeDTOResponse);
            iTraceabilityFeignHandler.writeTraceability(buildTraceabilityDTORequest(orderDTOResponse,employeeDTOResponse));
            return orderDTOResponse;
        } catch (UsernameNotFoundException ex){
            throw new DomainException(ConstantsErrorMessage.USER_NOT_FOUD + "{}" + ex);
        }
    }

    private TraceabilityDTORequest buildTraceabilityDTORequest(OrderDTOResponse orderDTOResponse, UserDTOResponse userDTOResponse) {
        log.info(ConstantsErrorMessage.BUILDING_TRACEABILITY_DTO_REQUEST);
        return TraceabilityDTORequest.builder()
                .idOrder(orderDTOResponse.getIdOrder())
                .idClient(orderDTOResponse.getClientDTOResponse().getIdUser())
                .emailClient(orderDTOResponse.getClientDTOResponse().getEmail())
                .newStatus(orderDTOResponse.getStatusOrder())
                .idEmployee(userDTOResponse.getIdUser())
                .emailEmployee(userDTOResponse.getEmail())
                .build();
    }
}
