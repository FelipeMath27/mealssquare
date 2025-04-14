package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.CategoryDTOResponse;

public interface ICategoryHandler {
    CategoryDTOResponse getCategoryDTOById(Long idCategory);
    CategoryDTOResponse getCategoryDTOByName(String nameCategory);
}
