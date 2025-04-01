package com.pragma.mealssquare.application.dto;

public class RestaurantDTORequest {
    private String nameRestaurant;
    private String addressRestaurant;
    private Long idOwner;
    private String phoneNumberRestaurant;
    private String urlLogo;
    private String nit;

    public RestaurantDTORequest() {
    }

    public RestaurantDTORequest(String nameRestaurant, String addressRestaurant, Long idOwner, String phoneNumberRestaurant, String urlLogo, String nit) {
        this.nameRestaurant = nameRestaurant;
        this.addressRestaurant = addressRestaurant;
        this.idOwner = idOwner;
        this.phoneNumberRestaurant = phoneNumberRestaurant;
        this.urlLogo = urlLogo;
        this.nit = nit;
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
}
