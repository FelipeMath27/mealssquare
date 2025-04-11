package com.pragma.mealssquare.application.mapper;

import com.pragma.mealssquare.application.dto.CategoryDTORequest;
import com.pragma.mealssquare.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryRequestMapper {
    Category toCategory(CategoryDTORequest categoryDTORequest);
}
