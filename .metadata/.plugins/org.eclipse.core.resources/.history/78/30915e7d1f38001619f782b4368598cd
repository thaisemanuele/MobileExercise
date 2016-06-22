package com.app.jobapplication.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Thais on 21/06/2016.
 */
public class ApplicationUtils {

    public static void showToastMessage(Context context, int message, int duration){
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public static Boolean checkConnection(Context context){
        ConnectionDetector detector= new ConnectionDetector(context);
        if (detector.isConnected()){
            return true;
        }
        else return false;
     }

}
