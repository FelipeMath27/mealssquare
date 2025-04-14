package com.pragma.mealssquare.infraestructure.output.mapper;

import com.pragma.mealssquare.domain.model.Category;
import com.pragma.mealssquare.infraestructure.output.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {
    CategoryEntity toCategoryEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);
}
