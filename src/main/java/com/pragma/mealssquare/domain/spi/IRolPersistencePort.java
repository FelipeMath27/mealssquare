package com.pragma.mealssquare.domain.spi;

import com.pragma.users.domain.model.Rol;

import java.util.List;

public interface IRolPersistencePort {
    void saveRol(Rol rol);

    List<Rol> getAllRoles();

    Rol getRol(String name_rol);

    void updateRol(Rol rol);

    void deleteRol(String name_rol);
}
