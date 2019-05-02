package com.limeade.timeapp.services.timezone;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Singleton that helps TimezoneServiceImpl
 */

public class GoogleTimezoneService {

    // Personal API key
    private static final String APIKEY = "&key=AIzaSyBiMB4oXdiU7tjRkR9bU09cOI-lItd6I38";
    // Google Timezone API URL
    private static final String TIMEZONEURL = "https://maps.googleapis.com/maps/api/timezone/";
    // Defined as the seconds since Jan 1, 1970. Set to zero for practical purposes.
    private static final String TIMESTAMP = "&timestamp=0";
    // Defines format of returned API response
    private static final String FORMAT = "json?";

    // Builds Timezone API request from latitude and longitude and required request parameters
    public static String createTimezoneRequest(String latitude, String longitude ) {
        StringBuilder query = new StringBuilder(TIMEZONEURL);
        query.append(FORMAT).append("location="+latitude+","+longitude)
                .append(TIMESTAMP).append(APIKEY);
        return query.toString();
    }

    // Extracts and returns timezoneID from API response,
    // returns null on JSONException
    public static String getTimeZoneId(String response) {
        try {
            JSONObject timeZone = new JSONObject(response);
            return timeZone.getString("timeZoneId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}