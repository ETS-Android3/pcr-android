package com.example.vishaalprasad.pcrapp;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Application Helper
 */
public class PcrApplication extends Application {

    private static Context mContext;

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        if (LeakCanary.isInAnalyzerProcess(this)) {

            /*  This process is dedicated to LeakCanary for heap analysis.
                You should not init your app in this process.  */

            return;
        }

        LeakCanary.install(this);
    }

    public static Context getContext() {
        return mContext;
    }

}
