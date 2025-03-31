package com.pragma.mealssquare.domain.api;

import com.pragma.users.domain.model.Rol;

import java.util.List;

public interface IRolServicePort {
    void saveRol(Rol rol);

    List<Rol> getAllRoles();

    Rol getRol(String name_rol);

    void updateRol(Rol rol);

    void deleteRol(String name_rol);
}
