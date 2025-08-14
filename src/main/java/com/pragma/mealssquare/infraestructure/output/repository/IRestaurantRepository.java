package com.pragma.mealssquare.infraestructure.output.repository;

import com.pragma.mealssquare.infraestructure.output.entity.RestaurantEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByNit(String nit);

    Optional<RestaurantEntity> findById(Long idRestaurant);

    Page<RestaurantEntity> findAllByOrderByNameAsc(Pageable pageable);
}
