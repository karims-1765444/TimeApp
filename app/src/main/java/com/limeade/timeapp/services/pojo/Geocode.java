package com.limeade.timeapp.services.pojo;

/**
 *  a POJO that encodes the geocode of a location
 */

public class Geocode {
    private String longitude;
    private String latitude;

    public Geocode(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public String getLatitude(){
        return latitude;
    }
}