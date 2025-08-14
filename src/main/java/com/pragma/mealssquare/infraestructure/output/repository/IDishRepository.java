package com.pragma.mealssquare.infraestructure.output.repository;

import com.pragma.mealssquare.infraestructure.output.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findByIdDish(Long idDish);
    Page<DishEntity> findAllByRestaurantEntity_IdRestaurantAndCategoryEntity_IdCategory(
            Long idRestaurant,
            Long idCategory,
            Pageable pageable
    );
}
