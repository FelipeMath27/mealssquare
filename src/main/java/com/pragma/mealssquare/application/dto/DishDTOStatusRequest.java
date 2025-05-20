package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.StatusDish;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DishDTOStatusRequest {
    private Long idDish;
    private StatusDish statusDish;
}
