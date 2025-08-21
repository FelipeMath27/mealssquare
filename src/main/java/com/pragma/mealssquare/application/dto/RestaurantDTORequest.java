package com.pragma.mealssquare.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTORequest {
    @NotBlank(message = "El nombre del restaurante es obligatorio")
    private String nameRestaurant;

    @NotBlank(message = "La dirección es obligatoria")
    private String addressRestaurant;

    @NotNull(message = "El id del propietario es obligatorio")
    private Long idOwner;

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String phoneNumberRestaurant;

    @NotBlank(message = "La URL del logo es obligatoria")
    private String urlLogo;

    @NotBlank(message = "El NIT es obligatorio")
    private String nit;
}
