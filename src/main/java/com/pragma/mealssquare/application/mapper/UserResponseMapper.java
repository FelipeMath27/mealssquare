package com.pragma.mealssquare.application.mapper;


import com.pragma.users.application.dto.RolDTOResponse;
import com.pragma.users.application.dto.UserDTOResponse;
import com.pragma.users.domain.model.Rol;
import com.pragma.users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {RolResponseMapper.class})
public interface UserResponseMapper {
    UserDTOResponse userDtoResponse(User user);

    List<UserDTOResponse> toResponseList(List<User> users);

    @Named("mapRolToDTO")
    static RolDTOResponse mapRolToDTO(Rol rol) {
        if (rol == null) {
            return null;
        }
        return new RolDTOResponse(rol.getIdRol(), rol.getNameRol(), rol.getDescriptionRol());
    }
}
