package com.pragma.mealssquare.domain.model;

public class Dish {
    private Long idDish;
    private String nameDish;
    private Category category;
    private String descriptionDish;
    private Double price;
    private Restaurant restaurant;
    private String urlImage;
    private StatusDish statusDish;

    public Dish() {
    }

    public Dish(Long idDish, String nameDish, Category category, String descriptionDish,
                Double price, Restaurant restaurant, String urlImage, StatusDish statusDish) {
        this.idDish = idDish;
        this.nameDish = nameDish;
        this.category = category;
        this.descriptionDish = descriptionDish;
        this.price = price;
        this.restaurant = restaurant;
        this.urlImage = urlImage;
        this.statusDish = statusDish;
    }

    public Long getIdDish() {
        return idDish;
    }

    public void setIdDish(Long idDish) {
        this.idDish = idDish;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescriptionDish() {
        return descriptionDish;
    }

    public void setDescriptionDish(String descriptionDish) {
        this.descriptionDish = descriptionDish;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public StatusDish getStatusDish() {
        return statusDish;
    }

    public void setStatusDish(StatusDish statusDish) {
        this.statusDish = statusDish;
    }
}
