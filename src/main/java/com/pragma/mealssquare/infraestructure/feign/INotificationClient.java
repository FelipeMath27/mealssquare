package com.pragma.mealssquare.infraestructure.feign;

import com.pragma.mealssquare.application.dto.NotificationDTORequest;
import com.pragma.mealssquare.application.dto.NotificationDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification-service", url = "${notifications.service.url}")
public interface INotificationClient {
    @PostMapping("/messaging/send-sms")
    NotificationDTOResponse sendSMS(NotificationDTORequest notificationDTORequest);
}
