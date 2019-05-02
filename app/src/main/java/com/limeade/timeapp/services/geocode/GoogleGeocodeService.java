package com.limeade.timeapp.services.geocode;

import com.limeade.timeapp.services.pojo.Geocode;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Singleton that helps GeocodeServiceImpl
 */

public final class GoogleGeocodeService {

    // Google Geocode API URL
    private static final String GEOCODEURL = "https://maps.googleapis.com/maps/api/geocode/";
    // Personal API key
    private static final String APIKEY = "&key=AIzaSyBiMB4oXdiU7tjRkR9bU09cOI-lItd6I38";
    // Defines format of returned API response
    private static final String FORMAT = "json?";

    // Builds Geocode API request from location name and required request parameters
    public static String createGeocodeRequest(String locationName) {
        StringBuilder request = new StringBuilder(GEOCODEURL);
        request.append(FORMAT).append("address="+locationName).append(APIKEY);
        return request.toString();
    }

    // Extracts latitude and longitude from API response and returns
    // them as a Geocode object, returns null on JSONException
    public static Geocode getGeocodeFromResponse(String googleResponse) {
        try {
            JSONObject locationInfo = new JSONObject(googleResponse);
            JSONObject results = locationInfo.getJSONArray("results").getJSONObject(0);
            JSONObject location = results.getJSONObject("geometry").getJSONObject("location");
            return new Geocode(location.getString("lat"),
                    location.getString("lng"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}