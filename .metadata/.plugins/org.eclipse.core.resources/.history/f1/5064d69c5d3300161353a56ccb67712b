package com.app.jobapplication.chargebackexercise;

import java.io.InputStream;

import android.os.AsyncTask;
import android.util.Log;

public class RetrieveStream extends AsyncTask<String, Integer, InputStream>{
	
	private static final String LOG = "<Async>RetrieveStream";

	@Override
	protected InputStream doInBackground(String... params) {
		ConnectionHelper connection = new ConnectionHelper();
		connection.getNotice();
		connection.getChargeBack();
		return null;
	}
	
	protected void onPostExecute() {
        Log.i(LOG, "Finished ");
    }

}
