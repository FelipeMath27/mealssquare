package com.pragma.mealssquare.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTORequest {
    private Long idCategory;
    private String nameCategory;
    private String descriptionCategory;
}
