package com.sofdigitalhackathon.libertypolls.model;

import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("phone")
    String phone;
    @SerializedName("inn")
    String inn;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getInn() {
        return inn;
    }
}
