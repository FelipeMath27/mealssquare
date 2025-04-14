package com.pragma.mealssquare.infraestructure.input.rest;

import com.pragma.mealssquare.application.dto.CategoryDTOResponse;
import com.pragma.mealssquare.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryHandler iCategoryHandler;

    @GetMapping("/{idCategory}")
    public ResponseEntity<CategoryDTOResponse> getCategoryById(@PathVariable Long idCategory){
        CategoryDTOResponse categoryDTOResponse =iCategoryHandler.getCategoryDTOById(idCategory);
        return ResponseEntity.ok(categoryDTOResponse);
    }
}
