package com.pragma.mealssquare.infraestructure.output.repository;

import com.pragma.mealssquare.infraestructure.output.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByIdClient(Long idClient);
    Page<OrderEntity> findAllByRestaurantEntity_IdRestaurantAndStatusOrder(
            Long idRestaurant,
            String statusOrder,
            Pageable pageable
    );
}
