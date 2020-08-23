package com.sofdigitalhackathon.libertypolls.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Flat {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public int getFlatNum() {
        return flatNum;
    }

    public void setFlatNum(int flatNum) {
        this.flatNum = flatNum;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @SerializedName("id")
    int id;
    @SerializedName("adress")
    int buildingId;
    @SerializedName("square")
    double square;
    @SerializedName("number")
    int flatNum;
    @SerializedName("adressinfo")
    Building building;

    @NonNull
    @Override
    public String toString() {
        return "Кв. " + String.valueOf(flatNum);
    }
}
