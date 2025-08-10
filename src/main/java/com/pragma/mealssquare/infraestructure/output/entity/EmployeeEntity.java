package com.pragma.mealssquare.infraestructure.output.entity;

import com.pragma.mealssquare.domain.model.StatusEmployee;
import com.pragma.mealssquare.domain.model.TypePositionEmployee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "PRG_TBL_EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmployee")
    private Long idEmployee;

    @NotNull(message = "id user cannot be null")
    @Column(name = "idUser")
    private Long idUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRestaurant", nullable = false)
    private RestaurantEntity restaurantEntity;

    @NotNull(message = "entry date cannot be null")
    @Column(name = "entryDate")
    private LocalDate entryDate;

    @NotNull(message = "position cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "positionEmployee")
    private TypePositionEmployee typePositionEmployee;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusEmployee")
    private StatusEmployee statusEmployee;
}
