package com.limeade.timeapp.services.geocode;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.common.base.Function;
import com.limeade.timeapp.services.pojo.Geocode;
import com.limeade.timeapp.services.RequestManager;

/**
 * Singleton that implements GeocodeService using
 * Google Geocode API
 */

public class GeocodeServiceImpl implements GeocodeService {

    private static GoogleGeocodeService googleLocationService;

    public static void getLatitudeLongitude(String locationName,
                                            final Function<Geocode, Void> completionHandler){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                googleLocationService.createGeocodeRequest(locationName),
                response -> {
                    // Geocode API success, returns Geocode to completion handler
                    completionHandler.apply(googleLocationService.getGeocodeFromResponse(response));},
                error -> {
                    // Geocode API failed, returns null to error handler
                    completionHandler.apply(null);
                });
        RequestManager.addRequest(stringRequest);
    }
}

