package com.limeade.timeapp.Manager;

import android.util.Log;

import com.google.common.base.Function;
import com.limeade.timeapp.External.GoogleMapsClient;

public class LocationInfo {

    private String longitude;
    private String latitude;
    private String locationName;
    private String timeZoneId;

    public LocationInfo(String locationName){
        this.locationName = locationName;
        this.timeZoneId = "";
        this.latitude = "";
        this.longitude = "";
    }

    public  void setLocationName(final String newLocationName){ this.locationName = locationName; }

    public String getLocationName(){ return locationName; }

    //  Uses GoogleMapsClient to set location's latitude, longitude, and timeZoneId
    public void setTimeZoneId(final String newLocationName, final Function<String, Void> completionHandler) {
        GoogleMapsClient.getLatitudeLongitude(newLocationName, new Function<GoogleMapsClient.Geocode, Void>() {
            @Override
            public Void apply(GoogleMapsClient.Geocode location) {
                if (location == null){
                    Log.d("ERROR", "Location name invalid.");
                    completionHandler.apply(null);
                    return null;
                }
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                GoogleMapsClient.getTimezone(longitude, latitude, new Function<String, Void>() {
                    @Override
                    public Void apply(String timeZone) {
                        if (timeZone==null){
                            Log.d("ERROR", "Latitude and longitude invalid.");
                            completionHandler.apply(null);
                            return null;
                        }
                        // if both API's work, then location is valid
                        // so we set the new name and timezone accordingly
                        locationName = newLocationName;
                        timeZoneId = timeZone;
                        completionHandler.apply(timeZoneId);
                        return null;
                    }
                });
                return null;
            }
        });
    }
}
