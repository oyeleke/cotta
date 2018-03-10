package com.example.oyeleke.myapplication.Network;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.example.oyeleke.myapplication.application.App;

/**
 * Created by oyeleke on 3/10/18.
 */

public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private RequestQueue requestQueue;

    private VolleySingleton(){
        requestQueue = Volley.newRequestQueue(App.getAppContext());
        VolleyLog.DEBUG = false;
    }

    public static VolleySingleton getmInstance(){
        if(mInstance == null){
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return this.requestQueue;
    }
}
