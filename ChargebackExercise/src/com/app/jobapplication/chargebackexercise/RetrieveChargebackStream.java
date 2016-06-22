package com.app.jobapplication.chargebackexercise;

import android.os.AsyncTask;
import android.util.Log;

public class RetrieveChargebackStream extends AsyncTask<String, Integer, String>{
	
	private static final String LOG = "<Async>RetrieveStream";
	private AsyncResponse response; 
	
	/**
	 * Constructor
	 * @param response
	 */
	public RetrieveChargebackStream(AsyncResponse response) {
		super();
		this.response = response;
	}

	@Override
	public String doInBackground(String... params) {
		
		String noticeString = "";
		noticeString = ConnectionHelper.getFromEndpoint(params[0]);
		return noticeString;
	}

	protected void onPostExecute(String result) {
        Log.d(LOG, "Finished ");
        response.processFinish(result);
    }
	
}
