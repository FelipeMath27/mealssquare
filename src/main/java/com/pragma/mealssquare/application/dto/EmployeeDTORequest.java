package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.StatusEmployee;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTORequest {
    private Long idRestaurant;
    private LocalDate dateCreateEmployee;
    private String positionEmployee;
    private StatusEmployee statusEmployee;
}
