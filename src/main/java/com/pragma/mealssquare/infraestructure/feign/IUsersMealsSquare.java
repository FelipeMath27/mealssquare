package com.pragma.mealssquare.infraestructure.feign;

import com.pragma.mealssquare.domain.model.Rol;
import com.pragma.mealssquare.domain.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "users-service", url = "${users.service.url}")
public interface IUsersMealsSquare {
    @GetMapping("/users/{email}")
    User getUserByEmail(@PathVariable("email") String email);

    @GetMapping("/roles/{nameRol}")
    Rol getRoleByName(@RequestParam("name") String nameRol);

    @GetMapping("roles/id/{idRol}")
    Rol getRoleById(@RequestParam("id") Long idRol);

    @GetMapping("users/id/{idUser}")
    User getUserById(@RequestParam("idUser") Long idUser);
}
