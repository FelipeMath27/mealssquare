package com.pragma.mealssquare.infraestructure.output.entity;

import com.pragma.mealssquare.domain.model.StatusOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRG_TBL_ORDER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrder", nullable = false)
    private Long idOrder;

    @NotNull(message = "The idClient cannot be null")
    @Column(name = "idClient", nullable = false)
    private Long idClient;

    @Column(name = "idEmployee")
    private Long idEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRestaurant", nullable = false)
    private RestaurantEntity restaurantEntity;

    @NotNull(message = "The date of the order cannot be null")
    @Column(name = "dateOrder", nullable = false)
    private LocalDate dateOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusOrder", nullable = false)
    private StatusOrder statusOrder;

    @OneToMany(mappedBy = "orderEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();

}
