package com.pragma.mealssquare.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetailDTORequest {
    @NotNull
    private Long idDish;
    @NotNull
    @Min(1)
    private Integer cantDish;
}
