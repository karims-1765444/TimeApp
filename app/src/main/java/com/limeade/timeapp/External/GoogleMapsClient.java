package com.limeade.timeapp.External;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.common.base.Function;

import org.json.JSONException;
import org.json.JSONObject;


public class GoogleMapsClient {


    private static String GEOCODEURL = "https://maps.googleapis.com/maps/api/geocode/";
    private static String APIKEY = "&key=AIzaSyBiMB4oXdiU7tjRkR9bU09cOI-lItd6I38";


    private static String TIMEZONEURL = "https://maps.googleapis.com/maps/api/timezone/";
    private static String TIMESTAMP = "&timestamp=0";


    private static String FORMAT = "json?";

    // Uses GoogleMaps Geocode API to get the latitude and longitude
    // of a location using its name
    public static void getLatitudeLongitude(String locationName, final Function<Geocode, Void> completionHandler){
        Log.d("DEBUG", "GetLatitudeLongitude called.");
        // Builds API query of URL, address, API key
        StringBuilder query = new StringBuilder(GEOCODEURL);
        query.append(FORMAT).append("address="+locationName).append(APIKEY);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                query.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DEBUG", "Geocode API success.");
                        Geocode location = null;
                        try {
                            // extracts latitude and longitude from API response
                            JSONObject locationInfo = new JSONObject(response);
                            JSONObject results = locationInfo.getJSONArray("results").getJSONObject(0);
                            JSONObject position = results.getJSONObject("geometry").getJSONObject("location");
                            location = new Geocode(position.getString("lat"),
                                    position.getString("lng"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        completionHandler.apply(location);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Geocode API failed, returns null to completion handler
                Log.d("ERROR", "Geocode API failure.");
                completionHandler.apply(null);
            }
        });
        RequestManager.addRequest(stringRequest);
        }

    // Uses GoogleMaps TimeZone API to get time zone ID
    // of a location using its latitude and longitude.
    public static void getTimezone(String longitude, String latitude , final Function<String, Void> completionHandler){
        Log.d("DEBUG", "getTimeZone called.");
        // Builds API query of URL, latitude, longitude, API key
        StringBuilder query = new StringBuilder(TIMEZONEURL);
        query.append(FORMAT).append("location="+latitude+","+longitude)
                .append(TIMESTAMP).append(APIKEY);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                query.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String timeZoneId = "";
                        try {
                            // extracts timeZone from API response
                            JSONObject timeZone = new JSONObject(response);
                            timeZoneId = timeZone.getString("timeZoneId");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        completionHandler.apply(timeZoneId);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TimeZone API failed, returns null to completion handler
                Log.d("ERROR", "TimeZone API failed");
                completionHandler.apply(null);
            }
        });
        RequestManager.addRequest(stringRequest);
    }
        // Inner class used to store the latitude and longitude of a location
        public static class Geocode {
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
    }

