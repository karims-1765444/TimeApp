package com.limeade.timeapp.services.location;


import com.google.common.base.Function;
import com.limeade.timeapp.services.GoogleClient;

/**
 *  A class that stores information about a location
 *  that could be a city, country, or any address.
 */

public class LocationInfo {


    private String locationName;
    private String timeZoneId;

    public LocationInfo(String locationName){ this.locationName = locationName; }

    public  void setLocationName(final String locationName){ this.locationName = locationName; }

    public void setTimeZone(String timeZoneId) { this.timeZoneId = timeZoneId; }

    public String getLocationName(){ return locationName; }

    // Changes clock time zone to the new location's and passes timezone
    // to completion handler
    public void setUpClock(String newLocationName, Function<String, Void> completionHandler){
        GoogleClient.getTimeZone(newLocationName, timeZoneId -> {
            if (timeZoneId==null){
                completionHandler.apply(null);
                return null;
            }
            // updates locationInfo and sets onscreen timezone
            this.locationName = newLocationName;
            this.timeZoneId = timeZoneId;
            completionHandler.apply(timeZoneId);
            return null;
        });
    }

}
