package com.pragma.mealssquare.domain.model;

public class OrderDish {
    private Long idOrderDish;
    private Order order;
    private Dish dish;
    private int quantity;

    public OrderDish() {
    }

    public OrderDish(Long idOrderDish, Order order, Dish dish, int quantity) {
        this.idOrderDish = idOrderDish;
        this.order = order;
        this.dish = dish;
        this.quantity = quantity;
    }

    public Long getIdOrderDish() {
        return idOrderDish;
    }

    public void setIdOrderDish(Long idOrderDish) {
        this.idOrderDish = idOrderDish;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
