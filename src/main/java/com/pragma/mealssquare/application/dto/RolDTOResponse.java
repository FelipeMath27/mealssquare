package com.pragma.mealssquare.application.dto;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolDTOResponse {
    private UUID id_rol;
    private String name_rol;
    private String description_rol;
}
