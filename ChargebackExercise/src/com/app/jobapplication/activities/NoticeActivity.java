package com.app.jobapplication.activities;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.jobapplication.chargebackexercise.ChargebackStarter;
import com.app.jobapplication.chargebackexercise.R;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoticeActivity extends Activity {
	
	private static final String LOG = "NoticeActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		if (savedInstanceState == null) {
			
			
			String message = "";
			Bundle extras = getIntent().getExtras(); 
   			if (extras != null) {
   				message = extras.getString("jsonString");
   			}
   			JSONObject jsonobject = null;
   			try {
				jsonobject = new JSONObject(message);
			} catch (JSONException e) {
				Log.e(LOG, "Error parsing "+message);
				jsonobject = new JSONObject();
			}
			Resources res = getResources();
			fillNoticeScreen(res, jsonobject);
			
		}
	}

	private void fillNoticeScreen(Resources res, final JSONObject jsonobject) {
		
		/*TextView to show notice title*/
		TextView noticeTitle = (TextView)this.findViewById(R.id.notice_title);
		
		/*TextView to show notice message*/
		TextView noticeField = (TextView) this.findViewById(R.id.notice_description);
		
		/*TextView for Primary Action*/
		TextView primaryAction = (TextView) this.findViewById(R.id.notice_action1);
		primaryAction.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v.getTag()!=null && v.getTag().toString().equalsIgnoreCase("continue")){
					try {
						JSONObject chargeobject = jsonobject.getJSONObject("links").getJSONObject("chargeback");
						String url = chargeobject.getString("href");
						ChargebackStarter message = new ChargebackStarter();
						message.startChargeback(url);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		/*TextView for Secondary Action*/
		TextView secondaryAction = (TextView) this.findViewById(R.id.notice_action2);
		
		try {
			/** JSONObject Values*/
			JSONObject primary_action = jsonobject.getJSONObject("primary_action");
			JSONObject secondary_action = jsonobject.getJSONObject("secondary_action");
			JSONObject links = jsonobject.getJSONObject("links");
			
			String title = jsonobject.getString("title");
			String description = jsonobject.getString("description");
			String action1 = primary_action.getString("action");
			String action2 = secondary_action.getString("action");
			String action1_title = primary_action.getString("title");
			String action2_title = secondary_action.getString("title");
			String formatted = String.format(res.getString(R.string.formatted_html), description);
			
			/** Setting texts to Views*/
			noticeTitle.setText(title);
			noticeField.setText(Html.fromHtml(formatted));
			primaryAction.setText(action1_title.toUpperCase());
			secondaryAction.setText(action2_title.toUpperCase());
			primaryAction.setTag(action1);
			secondaryAction.setTag(action2);
			
		} catch (JSONException e) {
			Log.e(LOG, "Error getting description");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notice, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_notice, container, false);
			return rootView;
		}
	}
}
