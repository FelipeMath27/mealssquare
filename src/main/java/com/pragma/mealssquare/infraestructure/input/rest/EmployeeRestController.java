package com.pragma.mealssquare.infraestructure.input.rest;

import com.pragma.mealssquare.application.dto.UserEmployeeDTORequest;
import com.pragma.mealssquare.application.handler.IEmployeeHandler;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.security.IJwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeRestController {
    private final IEmployeeHandler iEmployeeHandler;
    private final IJwtTokenProvider iJwtTokenProvider;

    @PostMapping("/create-employee")
    public ResponseEntity<Void> saveEmployee(@RequestBody @Valid UserEmployeeDTORequest userEmployeeDTORequest, Authentication authentication) {
        String email = authentication.getName();
        log.info(ConstantsErrorMessage.CREATING_EMPLOYEE);
        iEmployeeHandler.saveEmployee(userEmployeeDTORequest,email);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
