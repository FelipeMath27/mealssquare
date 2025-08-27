package com.pragma.mealssquare.infraestructure.input.rest;


import com.pragma.mealssquare.application.dto.OrderDTORequest;
import com.pragma.mealssquare.application.dto.OrderDTOResponse;
import com.pragma.mealssquare.application.dto.PageDTOResponse;
import com.pragma.mealssquare.application.handler.IOrderHandler;
import com.pragma.mealssquare.domain.model.StatusOrder;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderRestController {
    private final IOrderHandler iOrderHandler;

    @PostMapping("/create-order")
    public ResponseEntity<OrderDTOResponse> saveOrder(@RequestBody OrderDTORequest orderDTORequest){
        log.info(ConstantsErrorMessage.LISTENER_OK_CONTROLLER);
        OrderDTOResponse orderDTOResponse = iOrderHandler.saveOrder(orderDTORequest);
        return ResponseEntity.ok(orderDTOResponse);
    }

    @GetMapping("/list-order")
    public ResponseEntity<PageDTOResponse<OrderDTOResponse>> listOrder(@RequestParam int page,
                                                                       @RequestParam int size,
                                                                       @RequestParam StatusOrder statusOrder,
                                                                       Authentication authentication){
        log.info("{}",ConstantsErrorMessage.LISTENER_OK_CONTROLLER);
        return ResponseEntity.ok(iOrderHandler.getAllOrders(page,size,statusOrder,authentication.getName()));
    }

    @PatchMapping
    public ResponseEntity<OrderDTOResponse> assignOrderToEmployee(@RequestParam Long idOrder,
                                                                  Authentication authentication){
        log.info("{}",ConstantsErrorMessage.LISTENER_OK_CONTROLLER);
        return ResponseEntity.ok(iOrderHandler.assignOrderToEmployee(idOrder,authentication.getName()));
    }
}
