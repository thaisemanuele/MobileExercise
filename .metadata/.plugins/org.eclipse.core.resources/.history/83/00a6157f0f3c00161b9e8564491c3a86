package com.app.jobapplication.models.vo;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.jobapplication.helper.StringPair;
import com.app.jobapplication.utils.JsonParser;

import android.util.Log;

public class ChargebackVO {
	
	private String id;
	private String title;
	private String commentHint;
	private Boolean autoblock;
	private Boolean blockStatus;
	private Map<String, StringPair> reasonDetails;
	private Map<String, StringPair> links;
	
	private static final String LOG = "ChargebackVO";

	
	/**
	 * Stores all the information needed to fill up the chargeback screen 
	 * @param object
	 */
	public ChargebackVO(JSONObject object){
		
		try {
			
			this.id = object.getString("id");
			this.title = object.getString("title");
			this.commentHint = object.getString("comment_hint");
			this.autoblock = Boolean.parseBoolean(object.getString("autoblock"));
			this.blockStatus = false;
			
			JSONArray reasonArray = object.getJSONArray("reason_details");
			JSONObject linksObject = object.getJSONObject("links");
			
			this.reasonDetails = JsonParser.parseArray(reasonArray);
			this.links = JsonParser.parseObject(linksObject);
			
		} catch (JSONException e) {
			Log.e(LOG, "Error parsing JSON");
		}	
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getCommentHint() {
		return commentHint;
	}

	public Boolean isAutoblock() {
		return autoblock;
	}
	
	public Boolean toggleStatus(){
		blockStatus = !blockStatus;
		return blockStatus;
	}
	
	public Boolean getBlockStatus(){
		return blockStatus;
	}

	public String getReasonDetailsbyKey(String key){
		return this.reasonDetails.get(key).getValue();
	}
	
	public String getLinksbyKey(String key){
		return this.links.get(key).getValue();
	}

}
