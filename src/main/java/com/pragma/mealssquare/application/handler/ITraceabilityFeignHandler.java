package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.TraceabilityDTORequest;

public interface ITraceabilityFeignHandler {
    void writeTraceability(TraceabilityDTORequest traceabilityDTORequest);
}
