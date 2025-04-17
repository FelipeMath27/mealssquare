package com.pragma.mealssquare.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DishUpdateDTORequest {
    private Long idDish;
    private String dishDescription;
    private Double priceDish;
}
