package com.pragma.mealssquare.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTOResponse {
    private Long idRestaurant;
    private String nameRestaurant;
    private String addressRestaurant;
    private Long idOwner;
    private String phoneNumberRestaurant;
    private String urlLogo;
    private String nit;
}
