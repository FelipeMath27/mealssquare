package com.pragma.mealssquare.infraestructure.feign;

import com.pragma.mealssquare.application.dto.TraceabilityDTORequest;
import com.pragma.mealssquare.application.handler.ITraceabilityFeignHandler;
import com.pragma.mealssquare.infraestructure.constant.ConstantInfrastructure;
import com.pragma.mealssquare.infraestructure.exceptions.MicroserviceConnectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class TraceabilityFeignClient implements ITraceabilityFeignHandler {
    private final ITraceabilityClient iTraceabilityClient;

    @Override
    public void writeTraceability(TraceabilityDTORequest traceabilityDTORequest) {
        try {
            log.info(ConstantInfrastructure.SENDING_TRACEABILITY_DATA);
            iTraceabilityClient.createTraceability(traceabilityDTORequest);
            log.info(ConstantInfrastructure.SUCCESSFUL_TRACEABILITY_DATA);
        } catch (feign.FeignException ex){
            log.error("{}{}", ConstantInfrastructure.ERROR_CONNECT_TRACEABILITY_FEIGN, ex.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_CONNECT_TRACEABILITY_FEIGN, ex);
        } catch (Exception e){
            log.error("{}{}", ConstantInfrastructure.ERROR_TRACEABILITY, e.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_TRACEABILITY, e);
        }
    }
}
