package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOResponse {
    private Long idOrder;
    private RestaurantDTOResponse restaurantDTOResponse;
    private UserDTOResponse clientDTOResponse;
    private LocalDate dateOrder;
    private StatusOrder statusOrder;
    private List<OrderDetailDTOResponse> orderDetailList;
    private Double totalPrice;
}
