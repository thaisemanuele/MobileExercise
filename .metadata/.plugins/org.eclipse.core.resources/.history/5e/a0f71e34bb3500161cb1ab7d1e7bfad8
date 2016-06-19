package com.app.jobapplication.chargebackexercise;

import android.os.AsyncTask;
import android.util.Log;

public class RetrieveStream extends AsyncTask<String, Integer, String>{
	
	private static final String LOG = "<Async>RetrieveStream";
	private AsyncResponse response; 
	
	/**
	 * Constructor
	 * @param response
	 */
	public RetrieveStream(AsyncResponse response) {
		super();
		this.response = response;
	}

	@Override
	protected String doInBackground(String... params) {
		ConnectionHelper connection = new ConnectionHelper();
		String noticeString = connection.getFromEndpoint(params[0]);
		return noticeString;
	}

	protected void onPostExecute(String result) {
        Log.d(LOG, "Finished ");
        response.processFinish(result);
    }

}
