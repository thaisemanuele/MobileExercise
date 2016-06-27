package com.app.jobapplication.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.jobapplication.helper.StringPair;

import android.util.Log;

/**
 * Class for handling JSON operations
 * 
 * @author Thais
 *
 */
public class JsonParser {

	private static final String LOG = "JsonParser";

	/**
	 * Parses a JSONObject into a Map of <String(object key), StringPair(Object
	 * key and value)>
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, StringPair> parse(JSONObject object) {

		Map<String, StringPair> map = new HashMap<String, StringPair>();

		Iterator<String> keys = object.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			String value;
			try {
				value = object.getString(key);
				StringPair pair = new StringPair(key, value);
				map.put(key, pair);
			} catch (JSONException e) {
				Log.e(LOG, "Error retrieving value from JSONObject");
			}
		}
		return map;
	}

	/**
	 * Parses a JSONObject containing another JSONObject inside into a Map of
	 * <String(object key), StringPair(innerObject key and value)>
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, StringPair> parseObject(JSONObject object) {

		Map<String, StringPair> map = new HashMap<String, StringPair>();
		try {
			Iterator<String> keys = object.keys();
			while (keys.hasNext()) {
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

	/**
	 * Parses a JSONArray into a Map of <String(id of the object in the array
	 * index), StringPair(id and title of the object in the array index)>
	 * 
	 * @param array
	 * @return map (Map<String, StringPair>)
	 */
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

	/**
	 * Retrieves all the inner URLs stored inside the links in the JSONObject
	 * 
	 * @param response
	 * @return
	 */
	public static String getInnerUrl(String response) {
		JSONObject object;
		try {
			object = new JSONObject(response);
			Map<String, StringPair> map = JsonParser.parseObject(object);
			if (map.containsKey("links")) {
				String links = map.get("links").getValue();
				Map<String, StringPair> innerMap = JsonParser.parse(new JSONObject(links));
				if (innerMap.containsKey("href")) {
					return innerMap.get("href").getValue();
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

}
