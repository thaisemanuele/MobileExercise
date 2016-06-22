package com.app.jobapplication.chargebackexercise;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.jobapplication.helper.StringPair;
import com.app.jobapplication.utils.ApplicationUtils;

public class Starter {
	
	private static final String LOG = "NoticeStarter";
	private String startString;
	
	/**
	 * Start
	 * SUCCESS: Receive the results of the Async Task 
	 */
	public void start(){
		
		RetrieveStream start = new RetrieveStream(new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
				if(result != null){
					String start = result;
					startString = start;
				}
			}

			@Override
			public void processFinish(Boolean result) {
				// TODO Auto-generated method stub
				
			}

			
		});
		start.execute("");
	}
	
	
	
	
}
