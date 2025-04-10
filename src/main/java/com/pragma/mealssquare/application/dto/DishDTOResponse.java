package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.Category;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.StatusDish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDTOResponse {
    private String nameDish;
    private Category category;
    private String descriptionDish;
    private Double price;
    private Restaurant restaurant;
    private String urlImage;
    private StatusDish statusDish;
}
