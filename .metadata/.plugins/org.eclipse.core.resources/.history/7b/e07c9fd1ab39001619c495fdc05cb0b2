package com.app.jobapplication.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by Thais on 21/06/2016.
 */
public class ApplicationUtils {
	
	private static final String LOG = "ApplicationUtils";

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

	/**
	 * Constructs the JSONObject with the required structure,
	 * "comment" -> String
	 * "reason_details" -> Array<HashMap<id (String),response (Boolean)>>
	 * @param comment
	 * @param reasonArray
	 * @return
	 */
	public static JSONObject getJSONObject(String comment, ArrayList<HashMap<String, Boolean>> reasonArray) {
		
		try {
			
			JSONObject result = new JSONObject();
			JSONArray details = new JSONArray();
			
			HashMap<String, Boolean> innerMap = reasonArray.get(0);
			Iterator it = innerMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        JSONObject obj = new JSONObject();
		        obj.put("id", pair.getKey());
		        obj.put("response", pair.getValue());
		        details.put(obj);
		    }
			result.put("comment", comment);
			result.put("reason_details", details);
			Log.d(LOG, result.toString(2));
			return result;
			
		} catch (JSONException e) {
			Log.e(LOG, "Unable to contruct request body");
		}
		return null;
	}

}
