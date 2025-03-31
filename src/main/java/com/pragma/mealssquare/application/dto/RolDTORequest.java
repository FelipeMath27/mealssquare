package com.pragma.mealssquare.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RolDTORequest {
    private String nameRol;
    private String descriptionRol;
}
