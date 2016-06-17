package com.app.jobapplication.chargebackexercise;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.app.JobApplication;
import com.app.jobapplication.activities.NoticeActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RetrieveMessage {
	
	private static final String LOG = "RetrieveMessage";
	
	/**
	 * StartNotice
	 * SUCCESS: Receive the results of the Async Task and starts new activity 
	 */
	public void startNotice(){
		
		RetrieveStream notice = new RetrieveStream(new AsyncResponse() {
			
			@Override
			public void processFinish(String result) {
				if(result != null){
					String noticeString = result;
					startNoticeActivity(noticeString);
				}
			}

			
		});
		notice.execute("");
	}
	
	/**
	 * StartNoticeActivity
	 * @param result
	 */
	private void startNoticeActivity(String result){
		Context context = JobApplication.getAppContext();
		Intent intent = new Intent(context, NoticeActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("jsonString", result);
		context.startActivity(intent);
	}
	
}
