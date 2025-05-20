package com.pragma.mealssquare.application.dto;

import jakarta.validation.Valid;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEmployeeDTORequest {
    @Valid
    private EmployeeDTORequest employeeDTORequest;

    @Valid
    private UserDTORequest userDTORequest;
}
