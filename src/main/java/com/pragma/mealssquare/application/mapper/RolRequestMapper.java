package com.pragma.mealssquare.application.mapper;

import com.pragma.users.application.dto.RolDTORequest;
import com.pragma.users.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface RolRequestMapper {
    Rol toRol(RolDTORequest rolDTORequest);
}
