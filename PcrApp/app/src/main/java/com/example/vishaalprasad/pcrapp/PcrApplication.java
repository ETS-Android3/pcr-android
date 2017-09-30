package com.example.vishaalprasad.pcrapp;

import android.app.Application;
import android.content.Context;

/**
 * Application Helper
 */
public class PcrApplication extends Application {

    private static Context mContext;

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
