package com.pragma.mealssquare.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTOResponse {
    private Long idOrderDetail;
    private DishDTOResponse dishDTOResponse;
    private Integer cantDish;
    private Double subTotalPrice;
}
