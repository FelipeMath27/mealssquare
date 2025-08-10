package com.pragma.mealssquare.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEmployeeDTOResponse {
    private UserDTOResponse userDTOResponse;
    private EmployeeDTOResponse employeeDTOResponse;
}
