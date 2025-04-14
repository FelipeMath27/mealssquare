package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.StatusDish;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DishDTORequest {
    private String nameDish;
    private Long idCategory;
    private String dishDescription;
    private Double priceDish;
    private Long idRestaurant;
    private String urlImageDish;
    private StatusDish statusDish;
}
