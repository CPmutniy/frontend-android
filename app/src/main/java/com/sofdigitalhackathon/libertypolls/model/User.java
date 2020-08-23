package com.sofdigitalhackathon.libertypolls.model;

import android.util.Base64;

import com.google.gson.annotations.SerializedName;

import java.security.PublicKey;

public class User {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("surname")
    String surname;
    @SerializedName("patronymic")
    String patronymic;


    @SerializedName("flat")
    int flatId;
    @SerializedName("flatinfo")
    Flat flat;
    @SerializedName("state")
    boolean activated;



    @SerializedName("publick_key")
    String public_key;

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
    public int getFlatId() {
        return flatId;
    }

    public boolean isActivated() {
        return activated;
    }

    public byte[] getPublicDecoded_key() {
        return Base64.decode(public_key,Base64.DEFAULT);
    }
    public String getPublic_key() {
        return public_key.replaceAll("\r","");
    }
    public Flat getFlat() {
        return flat;
    }
}
