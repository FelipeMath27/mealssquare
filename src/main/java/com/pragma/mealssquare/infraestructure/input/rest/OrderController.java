package com.pragma.mealssquare.infraestructure.input.rest;


import com.pragma.mealssquare.application.dto.OrderDTORequest;
import com.pragma.mealssquare.application.dto.OrderDTOResponse;
import com.pragma.mealssquare.application.handler.IOrderHandler;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final IOrderHandler iOrderHandler;

    @PostMapping("/create-order")
    public ResponseEntity<OrderDTOResponse> saveOrder(@RequestBody OrderDTORequest orderDTORequest){
        log.info(ConstantsErrorMessage.LISTENER_OK_CONTROLLER);
        OrderDTOResponse orderDTOResponse = iOrderHandler.saveOrder(orderDTORequest);
        return ResponseEntity.ok(orderDTOResponse);
    }
}
