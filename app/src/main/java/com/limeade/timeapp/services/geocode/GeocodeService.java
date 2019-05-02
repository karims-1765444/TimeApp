package com.limeade.timeapp.services.geocode;

import com.google.common.base.Function;
import com.limeade.timeapp.services.pojo.Geocode;

/**
 * Interface that defines a Geocode Service
 */

public interface GeocodeService {

    // Asynchronously convert location name to geocode and pass it
    // to a callback completion handler
    static void getLatitudeLongitude(String locationName,
                                            final Function<Geocode, Void> completionHandler){}
}
