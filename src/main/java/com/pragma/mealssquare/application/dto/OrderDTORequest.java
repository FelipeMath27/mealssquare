package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.StatusOrder;
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
    @NotNull
    private LocalDate dateOrder;
    @NotNull
    private List<OrderDetailDTORequest> orderDetailList;
}
