package com.pragma.mealssquare.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NotificationDTORequest {
    private String phoneNumber;
}
