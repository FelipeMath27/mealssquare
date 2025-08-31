package com.pragma.mealssquare.infraestructure.feign;

import com.pragma.mealssquare.application.dto.TraceabilityDTORequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "traceability-service", url = "${traceability.service.url}")
public interface ITraceabilityClient {
    @PostMapping("/traceability/create-traceability")
    void createTraceability(@RequestBody TraceabilityDTORequest traceabilityDTORequest);
}
