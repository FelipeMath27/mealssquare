package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.application.dto.UserEmployeeDTORequest;
import com.pragma.mealssquare.application.mapper.IEmployeeUserRequestMapper;
import com.pragma.mealssquare.application.mapper.IUserResponseMapper;
import com.pragma.mealssquare.domain.api.IEmployeeServicePort;
import com.pragma.mealssquare.domain.model.Restaurant;
import com.pragma.mealssquare.domain.model.User;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeHandler implements IEmployeeHandler{
    private final IEmployeeServicePort iEmployeeServicePort;
    private final IUsersMealsSquare iUsersMealsSquare;
    private final IEmployeeUserRequestMapper iEmployeeUserRequestMapper;
    private final IUserFeignHandler iUserFeignHandler;
    private final IUserResponseMapper iUserResponseMapper;

    @Override
    public void saveEmployee(UserEmployeeDTORequest userEmployeeDTORequest, String email) {
        User userOwner;
        UserDTOResponse userDTOResponse;
        try {
            userDTOResponse = iUserFeignHandler.saveUserFeign(userEmployeeDTORequest.getUserDTORequest());
            userOwner = iUserResponseMapper.toUser(iUserFeignHandler.getUserByEmail(email));
        } catch (UsernameNotFoundException ex) {
            throw new InfrastructureException(ConstantsErrorMessage.USER_NOT_FOUD, ex);
        }
        iEmployeeServicePort.saveEmployee(iEmployeeUserRequestMapper.toEmployee(userEmployeeDTORequest.getEmployeeDTORequest()),
                iUserResponseMapper.toUser(userDTOResponse),userOwner);
    }
}
