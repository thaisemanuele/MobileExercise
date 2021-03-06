package com.app.jobapplication.activities;

import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.jobapplication.chargebackexercise.ChargebackStarter;
import com.app.jobapplication.chargebackexercise.R;
import com.app.jobapplication.models.vo.NoticeVO;
import com.app.jobapplication.utils.ApplicationUtils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Shows the Notice before allowing the user to access the Chargeback screen
 * 
 * @author Thais
 *
 */
public class NoticeActivity extends Activity {

	private Context context;
	private static final String LOG = "NoticeActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notice);
		context = getApplicationContext();
		if (savedInstanceState == null) {

			String message = "";
			String title = "";
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				message = extras.getString("jsonString");
				title = extras.getCharSequence("title").toString();
			}
			JSONObject jsonobject = null;
			try {
				jsonobject = new JSONObject(message);
				Resources res = getResources();
				fillNoticeScreen(res, jsonobject, title);
			} catch (JSONException e) {
				Log.e(LOG, "Error parsing " + message);
				ApplicationUtils.showToastMessage(context, R.string.error_generic, Toast.LENGTH_SHORT);
				finish();
			}
		}
	}

	private void fillNoticeScreen(Resources res, final JSONObject jsonobject, final String title) throws JSONException {

		/* TextView to show notice title */
		TextView noticeTitle = (TextView) this.findViewById(R.id.notice_title);

		/* TextView to show notice message */
		TextView noticeField = (TextView) this.findViewById(R.id.notice_description);

		/* TextView for Primary Action */
		TextView primaryAction = (TextView) this.findViewById(R.id.notice_action1);
		primaryAction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.getTag() != null && v.getTag().toString().equalsIgnoreCase("continue")) {
					try {
						JSONObject chargeobject = jsonobject.getJSONObject("links").getJSONObject("chargeback");
						String url = chargeobject.getString("href");
						if (ApplicationUtils.checkConnection(context)) {
							ChargebackStarter message = new ChargebackStarter();
							message.startChargeback(url, NoticeActivity.this, context, title);
						} else {
							ApplicationUtils.showToastMessage(context, R.string.error_not_connected, 10);
						}

					} catch (JSONException e) {
						ApplicationUtils.showToastMessage(context, R.string.error_generic, 10);
					}
				}

			}
		});
		/* TextView for Secondary Action */
		TextView secondaryAction = (TextView) this.findViewById(R.id.notice_action2);
		secondaryAction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.getTag() != null && v.getTag().toString().equalsIgnoreCase("cancel")) {
					finish();
				}

			}
		});

		NoticeVO notice = new NoticeVO(jsonobject);
		String formatted = String.format(res.getString(R.string.formatted_html), notice.getDescription());

		/** Setting texts to Views */
		noticeTitle.setText(notice.getTitle());
		noticeField.setText(Html.fromHtml(formatted));
		primaryAction.setText(notice.getPrimarybyKey("title").toUpperCase(Locale.getDefault()));
		secondaryAction.setText(notice.getSecondarybyKey("title").toUpperCase(Locale.getDefault()));
		primaryAction.setTag(notice.getPrimarybyKey("action"));
		secondaryAction.setTag(notice.getSecondarybyKey("action"));
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
}
