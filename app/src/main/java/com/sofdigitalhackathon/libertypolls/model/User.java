package com.sofdigitalhackathon.libertypolls.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("surname")
    String surname;
    @SerializedName("patronymic")
    String patronymic;
    @SerializedName("adress")
    int houseId;
    @SerializedName("state")
    boolean activated;
    @SerializedName("publick_key")
    byte[] public_key;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getHouseId() {
        return houseId;
    }

    public boolean isActivated() {
        return activated;
    }

    public byte[] getPublic_key() {
        return public_key;
    }
}
