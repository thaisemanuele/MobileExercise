package com.app.jobapplication.models.vo;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.jobapplication.helper.StringPair;
import com.app.jobapplication.utils.JsonParser;

import android.util.Log;

public class NoticeVO {

	private String title;
	private String description;
	private Map<String, StringPair> primary;
	private Map<String, StringPair> secondary;
	private Map<String, StringPair> links;

	private static final String LOG = "NoticeVO";

	/**
	 * Stores all the information needed to fill up the notice screen
	 * 
	 * @param object
	 */
	public NoticeVO(JSONObject object) {

		try {

			this.title = object.getString("title");
			this.description = object.getString("description");
			this.primary = JsonParser.parse(object.getJSONObject("primary_action"));
			this.secondary = JsonParser.parse(object.getJSONObject("secondary_action"));
			this.links = JsonParser.parseObject(object.getJSONObject("links"));

		} catch (JSONException e) {
			Log.e(LOG, "Error parsing JSON");
		}

	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getPrimaryKey(String key) {
		return this.primary.get(key).getKey();
	}

	public String getPrimarybyKey(String key) {
		return this.primary.get(key).getValue();
	}

	public String getSecondaryKey(String key) {
		return this.secondary.get(key).getKey();
	}

	public String getSecondarybyKey(String key) {
		return this.secondary.get(key).getValue();
	}

	public String getLinksbyKey(String key) {
		return this.links.get(key).getValue();
	}

}
