package com.pragma.mealssquare.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private Long idDish;
    private String nameDish;
    private Category category;
    private String descriptionDish;
    private Double price;
    private Restaurant restaurant;
    private String urlImage;
    private StatusDish statusDish;
}
