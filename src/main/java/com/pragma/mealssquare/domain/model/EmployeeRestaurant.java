package com.pragma.mealssquare.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRestaurant {
    private Long idEmployeeRestaurant;
    private Long idUser;
    private Long idRestaurant;
    private LocalDate dateCreateEmployee;
    private String positionEmployee;
    private StatusEmployee statusEmployee;
}
