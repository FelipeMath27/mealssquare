package com.pragma.mealssquare.infraestructure.output.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRG_TBL_RESTAURANT")
@SequenceGenerator(name = "restaurant_seq", sequenceName = "prg_tbl_restaurant_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRestaurant")
    private Long idRestaurant;

    @NotBlank(message = "Name restaurant cannot be blank")
    @Column(name = "nameRestaurant")
    private String nameRestaurant;

    @NotBlank(message = "Address restaurant cannot be blank")
    @Column(name = "addressRestaurant")
    private String addressRestaurant;

    @NotNull(message = "Id Owner restaurant cannot be null")
    @Column(name = "idOwner")
    private Long idOwner;

    @NotBlank(message = "Phone NumberRestaurant restaurant cannot be blank")
    @Column(name = "phoneNumberRestaurant")
    private String phoneNumberRestaurant;

    @NotBlank(message = "Url logo restaurant cannot be blank")
    @Column(name = "urlLogo")
    private String urlLogo;

    @NotBlank(message = "nit restaurant cannot be blank")
    @Column(name = "nit")
    private String nit;
}
