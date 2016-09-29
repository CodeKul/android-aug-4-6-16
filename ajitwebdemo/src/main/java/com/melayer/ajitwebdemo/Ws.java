package com.melayer.ajitwebdemo;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by aniruddha on 29/9/16.
 */
public class Ws {

    private static RequestQueue queue;

    public static RequestQueue queue(Context context){
        return queue == null ? Volley.newRequestQueue(context) : queue;
    }
}
