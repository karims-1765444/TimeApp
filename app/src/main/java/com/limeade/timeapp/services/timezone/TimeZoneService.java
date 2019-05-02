package com.limeade.timeapp.services.timezone;

import com.google.common.base.Function;

/**
 * Interface that defines a Timezone Service
 */

public interface TimeZoneService {

    // Asynchronously convert latitude and longitude to a timezone id
    // and pass it to a callback completion handler
    static void getTimezone(String longitude, String latitude,
                                   final Function<String, Void> completionHandler) {

    }
}
