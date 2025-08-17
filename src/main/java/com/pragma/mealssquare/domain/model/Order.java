package com.pragma.mealssquare.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long idOrder;
    private Long idClient;
    private Restaurant restaurant;
    private LocalDate dateOrder;
    private StatusOrder statusOrder;
    private List<OrderDetail> orderDetailList;
}


