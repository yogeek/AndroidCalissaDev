package com.yogidev.android.calissa;

import android.app.Application;
import android.content.Context;

/**
 * 
 * Class to retrieve the application context (used in the PreferencesManager singleton)
 * 
 * @author YoGi
 *
 */
public class CalissaApplication extends Application{
    private static final String TAG = CalissaApplication.class.getSimpleName();
    public static Context mContext = null;

    public CalissaApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}