package com.app;

import android.app.Application;
import android.content.Context;

public class JobApplication extends Application {

	private static Context context;

    public void onCreate() {
        super.onCreate();
        JobApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return JobApplication.context;
    }
}
