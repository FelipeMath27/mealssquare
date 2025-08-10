package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.application.dto.UserDTORequest;
import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.infraestructure.constant.ConstantInfrastructure;
import com.pragma.mealssquare.infraestructure.exceptions.MicroserviceConnectionException;
import com.pragma.mealssquare.infraestructure.feign.IUsersMealsSquare;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserFeignAdapter  {
    private final IUsersMealsSquare iUsersMealsSquare;

    public UserDTOResponse saveUser(UserDTORequest userDTORequest){
        try {
            log.info(ConstantInfrastructure.START_PROCESS_TO_CREATE_USER_FEIGN);
            return iUsersMealsSquare.createEmployee(userDTORequest);
        } catch (feign.FeignException ex){
            log.error("{}{}", ConstantInfrastructure.ERROR_CREATING_USER_FEIGN, ex.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_CREATING_USER_FEIGN, ex);
        } catch (Exception e){
            log.error("{}{}", ConstantInfrastructure.ERROR_CREATING_FEIGN_USER, e.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_CREATING_FEIGN_USER, e);
        }
    }

    public UserDTOResponse getUserById(Long id){
        try {
            log.info(ConstantInfrastructure.START_PROCESS_TO_GET_USER_BY_ID_FEIGN);
            return iUsersMealsSquare.getUserById(id);
        } catch (feign.FeignException ex){
            log.error("{}{}", ConstantInfrastructure.ERROR_GETTING_USER_BY_ID_FEIGN, ex.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_GETTING_USER_BY_ID_FEIGN, ex);
        } catch (Exception e){
            log.error("{}{}", ConstantInfrastructure.ERROR_GETTING_FEIGN_USER_BY_ID, e.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_GETTING_FEIGN_USER_BY_ID, e);
        }
    }

    public UserDTOResponse getUserByEmail(String email){
        try {
            log.info(ConstantInfrastructure.START_PROCESS_TO_GET_USER_BY_ID_FEIGN);
            return iUsersMealsSquare.getUserByEmail(email);
        } catch (feign.FeignException ex){
            log.error("{}{}", ConstantInfrastructure.ERROR_GETTING_USER_BY_EMAIL_FEIGN, ex.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_GETTING_USER_BY_EMAIL_FEIGN, ex);
        } catch (Exception e){
            log.error("{}{}", ConstantInfrastructure.ERROR_GETTING_FEIGN_USER_BY_EMAIL, e.getMessage());
            throw new MicroserviceConnectionException(ConstantInfrastructure.ERROR_GETTING_FEIGN_USER_BY_EMAIL, e);
        }
    }

}
