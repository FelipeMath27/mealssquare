package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.spi.IDishPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.CustomException;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import com.pragma.mealssquare.infraestructure.output.mapper.IDishEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IDishRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository iDishRepository;
    private final IDishEntityMapper iDishEntityMapper;

    @Override
    public void save(Dish dish) {
        try {
            iDishRepository.save( iDishEntityMapper.toDishEntity(dish));
        }catch (DataAccessException | PersistenceException ex){
            throw new InfrastructureException(ConstantsErrorMessage.CANT_SAVE_DISH, ex);
        }
    }

    @Override
    public Optional<Dish> findById(Long idDish) {
            return Optional.ofNullable(iDishEntityMapper.toDish(iDishRepository.findByIdDish(idDish)
                    .orElseThrow(() -> new CustomException(ConstantsErrorMessage.DISH_NOT_FOUND))));
    }

}
