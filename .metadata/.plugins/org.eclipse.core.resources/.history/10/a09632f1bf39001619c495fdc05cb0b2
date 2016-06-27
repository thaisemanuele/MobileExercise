package com.app.jobapplication.chargebackexercise;

import com.app.JobApplication;
import com.app.jobapplication.activities.ChargebackActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ChargebackStarter {
	
	private static final String LOG = "ChargebackStarter";
	
	/**
	 * StartChargeback
	 * SUCCESS: Receive the results of the Async Task and starts new activity 
	 */
	public void startChargeback(String url, Activity activity){
		
		RetrieveChargebackStream charge = new RetrieveChargebackStream(new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
				if(result != null){
					String chargeString = result;
					startChargebackActivity(chargeString);
				}
			}

			@Override
			public void processFinish(Boolean result) {
				
				
			}
			
		}, activity);
		charge.execute(url);
	}
	
	/**
	 * StartNoticeActivity
	 * @param result
	 */
	private void startChargebackActivity(String result) {
		
		Context context = JobApplication.getAppContext();
		Intent intent = new Intent(context, ChargebackActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("jsonString", result);
		context.startActivity(intent);
		
	}
	
}
