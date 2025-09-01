package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.NotificationDTORequest;
import com.pragma.mealssquare.application.dto.NotificationDTOResponse;

public interface INotificationFeignSMSHandler {
    NotificationDTOResponse sendSMS(NotificationDTORequest notificationDTORequest);
}
