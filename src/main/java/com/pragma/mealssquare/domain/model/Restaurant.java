package com.pragma.mealssquare.domain.model;

import java.util.List;

public class Restaurant {
    private Long idRestaurant;
    private String nameRestaurant;
    private String addressRestaurant;
    private Long idOwnerUser;
    private String phoneNumberRestaurant;
    private String urlLogo;
    private String nit;

    public Restaurant() {
    }

    public Restaurant(Long idRestaurant, String nameRestaurant, String addressRestaurant, Long idOwnerUser, String phoneNumberRestaurant,
                      String urlLogo, String nit) {
        this.idRestaurant = idRestaurant;
        this.nameRestaurant = nameRestaurant;
        this.addressRestaurant = addressRestaurant;
        this.idOwnerUser = idOwnerUser;
        this.phoneNumberRestaurant = phoneNumberRestaurant;
        this.urlLogo = urlLogo;
        this.nit = nit;
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

    public Long getIdOwnerUser() {
        return idOwnerUser;
    }

    public void setIdOwnerUser(Long idOwner) {
        this.idOwnerUser = idOwner;
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
}
