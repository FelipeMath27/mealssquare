package com.pragma.mealssquare.infraestructure.feign;

import com.pragma.mealssquare.application.dto.NotificationDTORequest;
import com.pragma.mealssquare.application.dto.NotificationDTOResponse;
import com.pragma.mealssquare.application.handler.INotificationFeignSMSHandler;
import com.pragma.mealssquare.infraestructure.constant.ConstantInfrastructure;
import com.pragma.mealssquare.infraestructure.exceptions.MicroserviceConnectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class NotificationFeignClient implements INotificationFeignSMSHandler {
    private final INotificationClient iNotificationClient;

    @Override
    public NotificationDTOResponse sendSMS(NotificationDTORequest notificationDTORequest) {
        try {
            log.info(ConstantInfrastructure.SEND_NOTIFICATION_DATA);
            NotificationDTOResponse response = iNotificationClient.sendSMS(notificationDTORequest);
            log.info(ConstantInfrastructure.SUCCESSFUL_NOTIFICATION_DATA);
            return response;
        } catch (feign.FeignException ex){
            log.error("{}{}", ConstantInfrastructure.ERROR_CONNECT_NOTIFICATION_FEIGN, ex.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_CONNECT_NOTIFICATION_FEIGN, ex);
        } catch (Exception e){
            log.error("{}{}", ConstantInfrastructure.ERROR_NOTIFICATION, e.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_NOTIFICATION, e);
        }
    }
}
