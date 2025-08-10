package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.StatusEmployee;
import com.pragma.mealssquare.domain.model.TypePositionEmployee;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTOResponse {
    private Long idEmployee;
    private Long idUser;
    private LocalDate entryDate;
    private RestaurantDTOResponse restaurantDTOResponse;
    private TypePositionEmployee typePositionEmployee;
    private StatusEmployee statusEmployee;
}
