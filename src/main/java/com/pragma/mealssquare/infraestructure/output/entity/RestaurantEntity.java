package com.pragma.mealssquare.infraestructure.output.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "PRG_TBL_RESTAURANT")
@SequenceGenerator(name = "restaurant_seq", sequenceName = "prg_tbl_restaurant_seq", allocationSize = 1)
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "idRestaurant")
    private Long idRestaurant;

    @NotBlank(message = "Name restaurant cannot be blank")
    @Column(name = "nameRestaurant")
    private String nameRestaurant;

    @NotBlank(message = "Address restaurant cannot be blank")
    @Column(name = "addressRestaurant")
    private String addressRestaurant;

    @NotBlank(message = "Id Owner restaurant cannot be blank")
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

    public RestaurantEntity() {
    }

    public RestaurantEntity(Long idRestaurant, String nameRestaurant, String addressRestaurant, Long idOwner, String phoneNumberRestaurant, String urlLogo, String nit) {
        this.idRestaurant = idRestaurant;
        this.nameRestaurant = nameRestaurant;
        this.addressRestaurant = addressRestaurant;
        this.idOwner = idOwner;
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
