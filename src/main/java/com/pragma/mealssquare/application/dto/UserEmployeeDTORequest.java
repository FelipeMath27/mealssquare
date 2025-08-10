package com.pragma.mealssquare.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEmployeeDTORequest {
    @NotNull(message = "User data must not be null")
    @Valid
    private EmployeeDTORequest employeeDTORequest;

    @NotNull(message = "Employee data must not be null")
    @Valid
    private UserDTORequest userDTORequest;
}
