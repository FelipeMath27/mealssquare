package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Category;

import java.util.Optional;

public interface ICategoryPersistencePort {
    Optional<Category> findById (Long idCategory);
    Optional<Category> findByName (String nameCategory);
}
