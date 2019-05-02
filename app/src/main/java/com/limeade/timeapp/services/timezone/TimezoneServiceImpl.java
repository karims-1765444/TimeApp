package com.limeade.timeapp.services.timezone;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.common.base.Function;
import com.limeade.timeapp.services.RequestManager;

/**
 * Singleton that implements TimezoneService using
 * Google Timezone API
 */

public class TimezoneServiceImpl implements TimeZoneService {

    private static GoogleTimezoneService googleTimezoneService;

    public static void getTimezone(String longitude, String latitude ,
                                   final Function<String, Void> completionHandler){
        // Builds API query of URL, latitude, longitude, API key
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                googleTimezoneService.createTimezoneRequest(latitude, longitude),
                response -> {
                    // Timezone API succeeded, returns timezoneId to completion handler
                    completionHandler.apply(GoogleTimezoneService.getTimeZoneId(response));
                },
                error -> {
                    // TimeZone API failed, returns null to completion handler
                    completionHandler.apply(null);
                });
        RequestManager.addRequest(stringRequest);
    }
}

