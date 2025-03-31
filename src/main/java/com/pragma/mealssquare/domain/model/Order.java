package com.pragma.mealssquare.domain.model;


import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long idOrder;
    private Long idClient;
    private LocalDate dateOrder;
    private StatusOrder state;
    private Long idChef;
    private Restaurant restaurant;
    private List<OrderDish> orderDishList;

    public Order() {
    }

    public Order(Long idOrder, Long idClient, LocalDate dateOrder, StatusOrder state, Long idChef, Restaurant restaurant, List<OrderDish> orderDishList) {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.dateOrder = dateOrder;
        this.state = state;
        this.idChef = idChef;
        this.restaurant = restaurant;
        this.orderDishList = orderDishList;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    public StatusOrder getState() {
        return state;
    }

    public void setState(StatusOrder state) {
        this.state = state;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderDish> getOrderDishList() {
        return orderDishList;
    }

    public void setOrderDishList(List<OrderDish> orderDishList) {
        this.orderDishList = orderDishList;
    }
}


