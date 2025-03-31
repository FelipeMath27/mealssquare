package com.pragma.mealssquare.application.handler;

import com.pragma.users.application.dto.UserDTORequest;
import com.pragma.users.application.dto.UserDTOResponse;

import java.util.List;

public interface IUserHandler {
    void saveUserDTO(UserDTORequest userDTORq);

    List<UserDTOResponse> getAllUserDTO();

    UserDTOResponse getUserDTO(String email);

    void updateUserDTO(UserDTORequest userDTORq);

    void deleteUserDTO(String email);
}
