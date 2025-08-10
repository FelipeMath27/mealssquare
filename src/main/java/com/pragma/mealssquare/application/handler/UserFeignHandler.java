package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.UserDTORequest;
import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.infraestructure.output.adapter.UserFeignAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserFeignHandler implements IUserFeignHandler{

    private final UserFeignAdapter userFeignAdapter;

    @Override
    public UserDTOResponse saveUserFeign(UserDTORequest userDTORequest) {
        return userFeignAdapter.saveUser(userDTORequest);
    }

    @Override
    public UserDTOResponse getUserById(Long id) {
        return userFeignAdapter.getUserById(id);
    }

    @Override
    public UserDTOResponse getUserByEmail(String email) {
        return userFeignAdapter.getUserByEmail(email);
    }
}
