package com.pragma.mealssquare.application.handler;

import com.pragma.mealssquare.application.dto.CategoryDTOResponse;
import com.pragma.mealssquare.application.mapper.ICategoryRequestMapper;
import com.pragma.mealssquare.application.mapper.ICategoryResponseMapper;
import com.pragma.mealssquare.domain.api.ICategoryServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryHandler implements ICategoryHandler{

    private final ICategoryServicePort iCategoryServicePort;
    private final ICategoryRequestMapper iCategoryRequestMapper;
    private final ICategoryResponseMapper iCategoryResponseMapper;

    @Override
    public CategoryDTOResponse getCategoryDTOById(Long idCategory) {
        return iCategoryResponseMapper.
                toResponse(iCategoryServicePort.getCategoryId(idCategory));
    }

    @Override
    public CategoryDTOResponse getCategoryDTOByName(String nameCategory) {
        return iCategoryResponseMapper.
                toResponse(iCategoryServicePort.getCategoryName(nameCategory));
    }
}
