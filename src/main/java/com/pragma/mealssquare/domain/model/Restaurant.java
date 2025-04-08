package com.pragma.mealssquare.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private Long idRestaurant;
    private String nameRestaurant;
    private String addressRestaurant;
    private Long idOwnerUser;
    private String phoneNumberRestaurant;
    private String urlLogo;
    private String nit;
}
