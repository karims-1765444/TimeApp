package com.limeade.timeapp.services;

import com.google.common.base.Function;
import com.limeade.timeapp.services.geocode.GeocodeServiceImpl;
import com.limeade.timeapp.services.timezone.TimezoneServiceImpl;

/**
 * Singleton that uses Google API's
 */

public class GoogleClient {

    static GeocodeServiceImpl locationServiceImpl;
    static TimezoneServiceImpl timezoneServiceImpl;

    // Asynchronously converts location name to a timeZoneID and passes
    // it to a completion handler
    public static void getTimeZone(final String locationName, final Function<String, Void> completionHandler) {
        locationServiceImpl.getLatitudeLongitude(locationName, location -> {
            if (location == null){
                completionHandler.apply(null);
                return null;
            }
            timezoneServiceImpl.getTimezone(location.getLongitude(), location.getLatitude(), timeZone -> {
                if (timeZone==null){
                    completionHandler.apply(null);
                    return null;
                }
                completionHandler.apply(timeZone);
                return null;
            });
            return null;
        });
    }
}

