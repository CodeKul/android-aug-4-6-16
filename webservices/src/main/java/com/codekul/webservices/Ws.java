package com.codekul.webservices;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by aniruddha on 13/9/16.
 */
public final class Ws {

    private static  RequestQueue queue;
    public static RequestQueue q(Context context){

        if(queue == null) queue = Volley.newRequestQueue(context);
        return queue;
    }
}
