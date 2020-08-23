package com.sofdigitalhackathon.libertypolls.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Building {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("building_type")
    String buildingType;
    @SerializedName("cad_number")
    String cadNumber;
    @SerializedName("company")
    int companyId;
    @SerializedName("votingsinfo")
    List<Poll> polls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getCadNumber() {
        return cadNumber;
    }

    public void setCadNumber(String cadNumber) {
        this.cadNumber = cadNumber;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public List<Poll> getPolls() {
        return polls;
    }

    @Override
    public String toString() {
        return name;
    }
}
