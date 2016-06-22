package com.app.jobapplication.utils;

import com.app.jobapplication.chargebackexercise.R;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
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
    
    public static void setForegroundAlpha(Activity activity, int frameId,int alpha) {
		
		FrameLayout frame = (FrameLayout) activity.findViewById( frameId);
		frame.getForeground().setAlpha(alpha);
		
	}

}
