package com.pragma.mealssquare.infraestructure.output.repository;

import com.pragma.mealssquare.infraestructure.output.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByIdCategory(Long idCategory);
    Optional<CategoryEntity> findByNameCategory(String nameCategory);
}
