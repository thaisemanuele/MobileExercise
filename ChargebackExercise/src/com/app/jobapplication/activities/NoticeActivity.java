package com.app.jobapplication.activities;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.jobapplication.chargebackexercise.R;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoticeActivity extends ActionBarActivity {
	
	private static final String LOG = "NoticeActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
			
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
			
			/*TextView to show notice title*/
			TextView noticeTitle = (TextView)this.findViewById(R.id.notice_title);
			
			/*TextView to show notice message*/
			TextView noticeField = (TextView) this.findViewById(R.id.notice_description);
			try {
				String title = jsonobject.getString("title");
				noticeTitle.setText(title);
				String rawString = jsonobject.getString("description");
				String formatted = String.format(res.getString(R.string.formatted_html), rawString);
				noticeField.setText(Html.fromHtml(formatted));
			} catch (JSONException e) {
				Log.e(LOG, "Error getting description");
				noticeField.setText("description");
			}
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
