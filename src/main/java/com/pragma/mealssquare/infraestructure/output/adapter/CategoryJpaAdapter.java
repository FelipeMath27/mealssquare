package com.pragma.mealssquare.infraestructure.output.adapter;

import com.pragma.mealssquare.domain.model.Category;
import com.pragma.mealssquare.domain.spi.ICategoryPersistencePort;
import com.pragma.mealssquare.domain.utils.ConstantsErrorMessage;
import com.pragma.mealssquare.infraestructure.output.entity.CategoryEntity;
import com.pragma.mealssquare.infraestructure.output.mapper.ICategoryEntityMapper;
import com.pragma.mealssquare.infraestructure.output.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository iCategoryRepository;
    private final ICategoryEntityMapper iCategoryEntityMapper;

    @Override
    public Optional<Category> findById(Long idCategory) {
        Optional<CategoryEntity> categoryEntityOptional = iCategoryRepository.findByIdCategory(idCategory);
        return Optional.ofNullable(categoryEntityOptional.map(iCategoryEntityMapper::toCategory)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ConstantsErrorMessage.CATEGORY_NOT_FOUND)));
    }

    @Override
    public Optional<Category> findByName(String nameCategory) {
        Optional<CategoryEntity> categoryEntityOptional = iCategoryRepository.findByNameCategory(nameCategory);
        return Optional.ofNullable(categoryEntityOptional.map(iCategoryEntityMapper::toCategory)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ConstantsErrorMessage.CATEGORY_NOT_FOUND)));
    }
}
