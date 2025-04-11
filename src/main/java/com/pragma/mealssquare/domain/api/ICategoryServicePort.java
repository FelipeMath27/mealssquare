package com.pragma.mealssquare.domain.api;

import com.pragma.mealssquare.domain.model.Category;

public interface ICategoryServicePort {
    Category getCategoryId (Long idCategory);
    Category getCategoryName (String nameCategory);
}
