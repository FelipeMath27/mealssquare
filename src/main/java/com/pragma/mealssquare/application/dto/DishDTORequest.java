package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.StatusDish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDTORequest {
    private String nameDish;
    private Long idCategory;
    private String descriptionDish;
    private Double price;
    private Long idRestaurant;
    private String urlImage;
    private StatusDish statusDish;
}
