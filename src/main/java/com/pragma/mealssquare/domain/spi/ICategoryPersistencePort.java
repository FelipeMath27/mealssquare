package com.pragma.mealssquare.domain.spi;

import com.pragma.mealssquare.domain.model.Category;

public interface ICategoryPersistencePort {
    Category getCategoryId (Long idCategory);
    Category getCategoryName (String nameCategory);
}
