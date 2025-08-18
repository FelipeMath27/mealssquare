package com.pragma.mealssquare.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTORequest {
    @NotNull
    private Long idClient;
    @NotNull
    private Long idRestaurant;
    private LocalDate dateOrder;
    @NotNull
    private List<OrderDetailDTORequest> orderDetailList;
}
