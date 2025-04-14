package com.pragma.mealssquare.infraestructure.output.entity;

import com.pragma.mealssquare.domain.model.StatusDish;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRG_TBL_DISH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDish", nullable = false)
    private Long idDish;

    @NotBlank(message = "The dish name cannot blank")
    @Column(name = "nameDish", nullable = false)
    private String nameDish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategory", nullable = false)
    private CategoryEntity categoryEntity;

    @NotBlank(message = "Dish description cannot be blank")
    @Column(name = "dishDescription", nullable = false)
    private String dishDescription;

    @NotNull(message = "The price cannot be null")
    @Column(name = "priceDish", nullable = false)
    private Double priceDish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRestaurant", nullable = false)
    private RestaurantEntity restaurantEntity;

    @NotBlank(message = "You need to put the urlImage of the dish")
    @Column(name = "urlImageDish", nullable = false)
    private String urlImageDish;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusDish")
    private StatusDish statusDish;
}
