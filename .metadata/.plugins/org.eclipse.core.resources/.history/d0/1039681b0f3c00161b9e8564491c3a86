package com.app.jobapplication.chargebackexercise;

import com.app.JobApplication;
import com.app.jobapplication.activities.NoticeActivity;
import com.app.jobapplication.interfaces.AsyncResponse;
import com.app.jobapplication.utils.ApplicationUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NoticeStarter {
	
	private static final String LOG = "NoticeStarter";
	
	/**
	 * StartNotice
	 * SUCCESS: Receive the results of the Async Task and starts new activity 
	 * FAILURE: Notifies the user the request has failed
	 * @param context 
	 * @param title 
	 */
	public void startNotice(String noticeurl, Activity activity, final Context context, final CharSequence title){
		RetrieveStream notice = new RetrieveStream(new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
				if(result != null){
					String noticeString = result;
					startNoticeActivity(noticeString, title);
				}
				else {
					ApplicationUtils.showToastMessage(context, R.string.error_request, Toast.LENGTH_SHORT);
				}
			}

			@Override
			public void processFinish(Boolean result) {
			}

			
		}, activity);
		notice.execute(noticeurl);
	}
	
	/**
	 * StartNoticeActivity
	 * @param result
	 * @param title 
	 */
	private void startNoticeActivity(String result, CharSequence title) {
		
		Context context = JobApplication.getAppContext();
		Intent intent = new Intent(context, NoticeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("jsonString", result);
		intent.putExtra("title", title);
		context.startActivity(intent);
		
	}
	
}
