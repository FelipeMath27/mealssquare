package com.pragma.mealssquare.infraestructure.output.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRG_TBL_ORDER_DETAIL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrderDetail", nullable = false)
    private Long idOrderDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOrder", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDish", nullable = false)
    private DishEntity dishEntity;

    @Column(name = "cantDish", nullable = false)
    private Integer cantDish;
}
