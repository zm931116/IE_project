package com.example.administrator.ie_project;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Administrator on 3/25/2018.
 */

public class Donor {
    private LatLng coordinate;
    private String name;
    private String description;
    private String availableTime;

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public String getCostAndConditions() {
        return costAndConditions;
    }

    public void setCostAndConditions(String costAndConditions) {
        this.costAndConditions = costAndConditions;
    }

    private String costAndConditions;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Donor(LatLng coordinate, String name){
        this.coordinate = coordinate;
        this.name = name;
    }

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
