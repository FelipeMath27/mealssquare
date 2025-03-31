package com.pragma.mealssquare.domain.model;

import java.util.List;

public class Restaurant {
    private Long idRestaurant;
    private String nameRestaurant;
    private String addressRestaurant;
    private Long idOwner;
    private String phoneNumberRestaurant;
    private String urlLogo;
    private String nit;
    private List<Order> orders;
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(Long idRestaurant, String nameRestaurant, String addressRestaurant, Long idOwner, String phoneNumberRestaurant,
                      String urlLogo, String nit, List<Order> orders, List<Dish> dishes) {
        this.idRestaurant = idRestaurant;
        this.nameRestaurant = nameRestaurant;
        this.addressRestaurant = addressRestaurant;
        this.idOwner = idOwner;
        this.phoneNumberRestaurant = phoneNumberRestaurant;
        this.urlLogo = urlLogo;
        this.nit = nit;
        this.orders = orders;
        this.dishes = dishes;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public String getAddressRestaurant() {
        return addressRestaurant;
    }

    public void setAddressRestaurant(String addressRestaurant) {
        this.addressRestaurant = addressRestaurant;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getPhoneNumberRestaurant() {
        return phoneNumberRestaurant;
    }

    public void setPhoneNumberRestaurant(String phoneNumberRestaurant) {
        this.phoneNumberRestaurant = phoneNumberRestaurant;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
