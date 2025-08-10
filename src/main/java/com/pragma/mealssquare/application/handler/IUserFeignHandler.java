package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.UserDTORequest;
import com.pragma.mealssquare.application.dto.UserDTOResponse;

public interface IUserFeignHandler {
    UserDTOResponse saveUserFeign(UserDTORequest userDTORequest);

    UserDTOResponse getUserById(Long id);

    UserDTOResponse getUserByEmail(String email);
}
