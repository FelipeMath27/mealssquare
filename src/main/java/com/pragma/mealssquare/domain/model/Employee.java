package com.pragma.mealssquare.domain.model;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long idEmployee;
    private Long idUser;
    private Restaurant restaurant;
    private LocalDate entryDate;
    private TypePositionEmployee typePositionEmployee;
    private StatusEmployee statusEmployee;
}
