package com.app.jobapplication.chargebackexercise;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import android.os.AsyncTask;
import android.util.Log;

public class RetrieveStream extends AsyncTask<String, Integer, String>{
	
	private static final String LOG = "<Async>RetrieveStream";
	private static final String encoding = "UTF-8";
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
		InputStream is = connection.getNotice();
		try {
			String noticeString = IOUtils.toString(is, encoding);
			return noticeString;
		} catch (IOException e) {
			Log.e(LOG, e.getMessage());
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	protected void onPostExecute(String result) {
        Log.d(LOG, "Finished ");
        response.processFinish(result);
    }

}
