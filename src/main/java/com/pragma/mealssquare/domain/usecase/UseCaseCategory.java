package com.pragma.mealssquare.domain.usecase;

import com.pragma.mealssquare.domain.api.ICategoryServicePort;
import com.pragma.mealssquare.domain.exception.DomainException;
import com.pragma.mealssquare.domain.model.Category;
import com.pragma.mealssquare.domain.spi.ICategoryPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UseCaseCategory implements ICategoryServicePort {

    private final ICategoryPersistencePort iCategoryPersistencePort;

    @Override
    public Category getCategoryId(Long idCategory) {
        return iCategoryPersistencePort.findById(idCategory)
                .orElseThrow(() -> new DomainException(ConstantsErrorMessage.CATEGORY_NOT_FOUND));
    }

    @Override
    public Category getCategoryName(String nameCategory) {
        return null;
    }
}
