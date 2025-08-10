package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.RolDTOResponse;
import com.pragma.mealssquare.domain.model.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRolResponseMapper {
    RolDTOResponse toResponse(Rol rol);

    @Mapping(source = "idRol", target = "idRol")
    @Mapping(source = "nameRol", target = "nameRol")
    @Mapping(source = "descriptionRol", target = "descriptionRol")
    Rol toRol(RolDTOResponse rolDTOResponse);


}
