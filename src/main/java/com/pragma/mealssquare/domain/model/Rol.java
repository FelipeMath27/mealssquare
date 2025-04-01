package com.pragma.mealssquare.domain.model;

public class Rol {
    private Long idRol;
    private String nameRol;
    private String descriptionRol;

    public Rol(Long idRol, String nameRol, String descriptionRol) {
        this.idRol = idRol;
        this.nameRol = nameRol;
        this.descriptionRol = descriptionRol;
    }

    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNameRol() {
        return nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    public String getDescriptionRol() {
        return descriptionRol;
    }

    public void setDescriptionRol(String descriptionRol) {
        this.descriptionRol = descriptionRol;
    }
}


