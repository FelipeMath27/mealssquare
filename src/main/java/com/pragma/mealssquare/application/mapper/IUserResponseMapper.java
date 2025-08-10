package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRolResponseMapper.class})
public interface IUserResponseMapper {
    UserDTOResponse toUserDtoResponse(User user);

    User toUser(UserDTOResponse userDTOResponse);

}
