package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Dish;
import com.pragma.mealssquare.domain.spi.IDishPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.exceptions.InfrastructureException;
import com.pragma.mealssquare.infraestructure.output.entity.DishEntity;
import com.pragma.mealssquare.infraestructure.output.mapper.IDishEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.IDishRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository iDishRepository;
    private final IDishEntityMapper iDishEntityMapper;


    @Override
    public void saveDish(Dish dish) {
        try {
            DishEntity dishEntity = iDishEntityMapper.toDishEntity(dish);
            iDishRepository.save(dishEntity);
        }catch (DataAccessException | PersistenceException ex){
            throw new InfrastructureException(ConstantsErrorMessage.CANT_SAVE_DISH, ex);
        }
    }

    @Override
    public Dish getDishById(Long idDish) {
        return null;
    }
}
