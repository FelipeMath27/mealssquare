package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Employee;
import com.pragma.mealssquare.domain.spi.IEmployeePersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import com.pragma.mealssquare.infraestructure.output.mapper.IEmployeeEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IEmployeeRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmployeeJpaAdapter implements IEmployeePersistencePort {

    private final IEmployeeRepository iEmployeeRepository;
    private final IEmployeeEntityMapper iEmployeeEntityMapper;

    @Override
    public void save(Employee employee) {
        try {
            iEmployeeRepository.save(iEmployeeEntityMapper.toEmployeeEntity(employee));
        } catch (DataAccessException | PersistenceException ex) {
            throw new InfrastructureException(ConstantsErrorMessage.CANT_SAVE_EMPLOYEE, ex);
        }
    }

    @Override
    public Optional<Employee> findById(Long idEmployee) {
        return Optional.empty();
    }
}
