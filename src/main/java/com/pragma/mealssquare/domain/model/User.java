package com.pragma.mealssquare.domain.model;

import java.time.LocalDate;

public class User {

    private Long idUser;
    private String nameUser;
    private String lastNameUser;
    private TypeDocumentEnum typeDocumentUser;
    private String documentUser;
    private String phoneNumberUser;
    private LocalDate dateBirthUser;
    private String email;
    private String password;
    private Rol rol;

    public User(Long idUser, String nameUser, String lastNameUser, TypeDocumentEnum typeDocumentUser, String documentUser,
                String phoneNumberUser, LocalDate dateBirthUser, String email, String password, Rol rol) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.lastNameUser = lastNameUser;
        this.typeDocumentUser = typeDocumentUser;
        this.documentUser = documentUser;
        this.phoneNumberUser = phoneNumberUser;
        this.dateBirthUser = dateBirthUser;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public TypeDocumentEnum getTypeDocumentUser() {
        return typeDocumentUser;
    }

    public void setTypeDocumentUser(TypeDocumentEnum typeDocumentUser) {
        this.typeDocumentUser = typeDocumentUser;
    }

    public String getDocumentUser() {
        return documentUser;
    }

    public void setDocumentUser(String documentUser) {
        this.documentUser = documentUser;
    }

    public String getPhoneNumberUser() {
        return phoneNumberUser;
    }

    public void setPhoneNumberUser(String phoneNumberUser) {
        this.phoneNumberUser = phoneNumberUser;
    }

    public LocalDate getDateBirthUser() {
        return dateBirthUser;
    }

    public void setDateBirthUser(LocalDate dateBirthUser) {
        this.dateBirthUser = dateBirthUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}