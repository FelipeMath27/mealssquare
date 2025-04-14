package com.pragma.mealssquare.infraestructure.output.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRG_TBL_CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private Long idCategory;

    @NotBlank(message = "Name category cannot blank")
    @Column(name = "nameCategory", nullable = false)
    private String nameCategory;

    @NotBlank(message = "Description category cannot blank")
    @Column(name = "descriptionCategory", nullable = false)
    private String descriptionCategory;
}
