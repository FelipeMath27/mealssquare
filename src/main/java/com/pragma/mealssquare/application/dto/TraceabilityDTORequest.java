package com.pragma.mealssquare.application.dto;

import com.pragma.mealssquare.domain.model.StatusOrder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TraceabilityDTORequest {
    private Long idOrder;
    private Long idClient;
    private String emailClient;
    private StatusOrder newStatus;
    private Long idEmployee;
    private String emailEmployee;
}
