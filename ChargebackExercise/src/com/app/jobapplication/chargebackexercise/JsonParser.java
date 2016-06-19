package com.app.jobapplication.chargebackexercise;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.jobapplication.helper.StringPair;

import android.util.Log;

public class JsonParser {
	
	private static final String LOG = "JsonParser";
	
	public static Map<String, StringPair> parse(JSONObject object) {

		Map<String, StringPair> map = new HashMap<String, StringPair>();
			
		Iterator<String> keys = object.keys();
		while(keys.hasNext()){
			String key = keys.next();
			String value;
			try {
				value = object.getString(key);
				StringPair pair = new StringPair(key, value);
				map.put(key, pair);
			} catch (JSONException e) {
				Log.e(LOG, "Error retrieving value from JSONObject");
				e.printStackTrace();
			}
		}		
		return map;
	}
	
	public static Map<String, StringPair> parseObject(JSONObject object) {

		Map<String, StringPair> map = new HashMap<String, StringPair>();
			try {
				Iterator<String> keys = object.keys();
				while(keys.hasNext()){
					String key = keys.next();
					JSONObject innerObj = object.getJSONObject(key);
					String innerkey = innerObj.keys().next();
					String innervalue = innerObj.getString(innerkey);
					StringPair value = new StringPair(innerkey, innervalue);
					map.put(key, value);
				}
			} catch (JSONException e) {
				Log.e(LOG, "Error retrieving data from JSONObject");
			}
		return map;
	}
	
	public static Map<String, StringPair> parseArray(JSONArray array) {

		Map<String, StringPair> map = new HashMap<String, StringPair>();

		for (int index = 0; index < array.length(); index++) {
			JSONObject obj;
			try {
				obj = array.getJSONObject(index);
				String id = obj.getString("id");
				String title = obj.getString("title");
				StringPair value = new StringPair(id, title);
				map.put(id, value);
			} catch (JSONException e) {
				Log.e(LOG, "Error retrieving data from JSONArray");
			}
		}
		return map;
	}

}
