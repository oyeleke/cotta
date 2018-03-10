package com.example.oyeleke.myapplication.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by oyeleke on 3/10/18.
 */

public class App extends Application {
    public static App mInstance;
    public static Context mContext;

    @Override
    public void onCreate(){
        super.onCreate();

        mInstance = this;
        setAppContext(getApplicationContext());
    }

    public static App getmInstance(){
        return mInstance;
    }
    private static void setAppContext(Context context){
        mContext = context;
    }

    public static Context getAppContext(){
        return mContext;
    }
}
