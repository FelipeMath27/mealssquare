package com.pragma.mealssquare.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Category {
    private UUID idCategory;
    private String nameCategory;
    private String descriptionCategory;

    public Category(UUID idCategory, String nameCategory, String descriptionCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.descriptionCategory = descriptionCategory;
    }
}
