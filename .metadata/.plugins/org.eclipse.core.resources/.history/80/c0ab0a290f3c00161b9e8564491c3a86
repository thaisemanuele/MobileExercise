package com.app.jobapplication.chargebackexercise;

import com.app.jobapplication.interfaces.AsyncResponse;
import com.app.jobapplication.utils.ConnectionHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Requests from the server the information to fill up the Chargeback screen
 * onPreExecute: Show Progress Dialog
 * doInBackground: Request information from the server
 * onPostExecute: Send the result and dismiss the progress dialog
 * 
 * @author Thais
 *
 */
public class RetrieveChargebackStream extends AsyncTask<String, Integer, String>{
	
	private static final String LOG = "<Async>RetrieveStream";
	private AsyncResponse response;
	private ProgressDialog progress; 
	
	/**
	 * Constructor
	 * @param response
	 */
	public RetrieveChargebackStream(AsyncResponse response, Activity activity) {
		super();
		this.response = response;
		this.progress = new ProgressDialog(activity);
        
	}
	 @Override
     protected void onPreExecute() {
         super.onPreExecute();
         progress.setMessage("Carregando...");
         progress.setIndeterminate(false);
         progress.setCancelable(true);
         progress.show();
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
        progress.dismiss();
    }
	
}
