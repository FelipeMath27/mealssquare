package com.pragma.mealssquare.infraestructure.feign;

import com.pragma.mealssquare.application.dto.UserDTORequest;
import com.pragma.mealssquare.application.dto.UserDTOResponse;
import com.pragma.mealssquare.domain.model.Rol;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "users-service", url = "${users.service.url}")
public interface IUsersMealsSquare {
    @GetMapping("/users/{email}")
    UserDTOResponse getUserByEmail(@PathVariable("email") String email);

    @GetMapping("/roles/{nameRol}")
    Rol getRoleByName(@RequestParam("name") String nameRol);

    @GetMapping("roles/id/{idRol}")
    Rol getRoleById(@RequestParam("id") Long idRol);

    @GetMapping("users/id/{idUser}")
    UserDTOResponse getUserById(@PathVariable("idUser") Long idUser);

    @PostMapping("users/create-employee")
    UserDTOResponse createEmployee(@RequestBody UserDTORequest userDTORequest);
}
