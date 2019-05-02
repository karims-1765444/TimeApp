package com.limeade.timeapp.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Singleton to manage request queue
 */
public class RequestManager {

    private static RequestQueue queue;

    // Instantiates a new RequestManager
    public static void instantiateRequestManager(Context appContext ) {
        if ( queue == null ) {
            queue = Volley.newRequestQueue(appContext);
        }
    }

    // Adds a request to the queue
    public static void addRequest(Request<String> request) {
        queue.add(request);
    }

}
