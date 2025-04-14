package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.Category;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.StatusDish;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DishDTOResponse {
    private String nameDish;
    private Category category;
    private String dishDescription;
    private Double priceDish;
    private Restaurant restaurant;
    private String urlImageDish;
    private StatusDish statusDish;
}
