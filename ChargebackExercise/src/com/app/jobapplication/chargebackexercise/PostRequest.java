package com.app.jobapplication.chargebackexercise;

import com.app.jobapplication.interfaces.OnComplete;
import com.app.jobapplication.utils.ConnectionHelper;

import android.os.AsyncTask;
import android.util.Log;

/**
 * DoInBackground:Sends a post request to the server 
 * OnPostExecute: Updates the result for the listener
 * 
 * @author Thais
 *
 */
public class PostRequest extends AsyncTask<String, Integer, Boolean> {

	private static final String LOG = "<Async>RetrieveStream";
	private OnComplete listener;

	/**
	 * Constructor
	 * 
	 * @param response
	 */
	public PostRequest(OnComplete listener) {
		super();
		this.listener = listener;
	}

	@Override
	protected Boolean doInBackground(String... params) {

		Boolean postresponse = ConnectionHelper.post(params[0], params[1]);
		return postresponse;
	}

	protected void onPostExecute(Boolean result) {
		Log.d(LOG, "Post Result: " + result);
		listener.onComplete(result);
	}

}
