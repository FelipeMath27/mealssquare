package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Employee;

import java.util.Optional;

public interface IEmployeePersistencePort {
    void save(Employee employee);
    Optional<Employee> findByIdUser(Long idUser);
    Optional<Employee> findById(Long idEmployee);
}
