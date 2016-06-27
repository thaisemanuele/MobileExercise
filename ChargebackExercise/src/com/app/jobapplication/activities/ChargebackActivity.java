package com.app.jobapplication.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.app.jobapplication.chargebackexercise.PostRequest;
import com.app.jobapplication.chargebackexercise.R;
import com.app.jobapplication.interfaces.OnComplete;
import com.app.jobapplication.models.vo.ChargebackVO;
import com.app.jobapplication.utils.ApplicationUtils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ChargebackActivity Activity for filling in the form and send information
 * about the chargeback request
 * 
 * @author Thais
 *
 */
public class ChargebackActivity extends Activity {

	private static final String LOG = "ChargebackActivity";

	private ChargebackVO chargeback;
	private PopupWindow popupLoader;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chargeback);
		context = getApplicationContext();

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
		} catch (JSONException e) {
			Log.e(LOG, "Error parsing " + message);
			jsonobject = new JSONObject();
		}
		Resources res = getResources();
		fillChargebackScreen(res, jsonobject, title);
	}

	/**
	 * fillChargebackScreen Set the correct values to be shown on the screen
	 * 
	 * @param res
	 * @param jsonobject
	 * @param title
	 */
	private void fillChargebackScreen(Resources res, JSONObject jsonobject, String title) {

		/* TextView to show title */
		TextView titleField = (TextView) this.findViewById(R.id.chargeback_title);

		/* TextView to show lock message */
		TextView messageField = (TextView) this.findViewById(R.id.chargeback_lock_message);

		/* TextView to show reason1 */
		final TextView reason1Field = (TextView) this.findViewById(R.id.chargeback_reason1);

		/* TextView to show reason2 */
		final TextView reason2Field = (TextView) this.findViewById(R.id.chargeback_reason2);

		/* EditText for submitting comments */
		EditText commentsField = (EditText) this.findViewById(R.id.chargeback_comments);

		/* ImageView for lock/unlock padlock */
		ImageView lockImage = (ImageView) this.findViewById(R.id.chargeback_padlock);

		/* Switches for reason1 and reason2 */
		Switch primeSwitch = (Switch) this.findViewById(R.id.reason_button1);
		Switch secondSwitch = (Switch) this.findViewById(R.id.reason_button2);

		/* TextView for the Cancel Button */
		TextView cancelButton = (TextView) this.findViewById(R.id.chargeback_cancel);

		/* TextView for the Confirm Button */
		TextView confirmButton = (TextView) this.findViewById(R.id.chargeback_confirm);
		confirmButton.setEnabled(false);

		/* Creating Listeners */
		TextWatcher txwatch = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				verifyEmptyField();
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		};
		/* Setting Listeners */
		commentsField.addTextChangedListener(txwatch);
		/**
		 * Initialising the switch widgets tag to indicate whether they display
		 * true(yes) or false(no)
		 */
		if (primeSwitch.getTag() == null) {
			primeSwitch.setTag(false);
		}
		if (secondSwitch.getTag() == null) {
			secondSwitch.setTag(false);
		}
		/* Setting Listeners */
		/**
		 * getColor deprecated for version 23, however, this app supports
		 * version 21
		 */
		primeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				buttonView.setTag(isChecked);
				Switch sw = (Switch) buttonView;
				TextView r1 = (TextView) reason1Field;
				if (isChecked) {
					sw.setTrackResource(R.drawable.ic_track_selected);
					r1.setTextColor(getResources().getColor(R.color.RequiredGreen));
				} else {
					sw.setTrackResource(R.drawable.ic_track_unselected);
					r1.setTextColor(getResources().getColor(R.color.RequiredBlackTexts));
				}
			}
		});
		secondSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				buttonView.setTag(isChecked);
				Switch sw = (Switch) buttonView;
				TextView r2 = (TextView) reason2Field;
				if (isChecked) {
					sw.setTrackResource(R.drawable.ic_track_selected);
					r2.setTextColor(getResources().getColor(R.color.RequiredGreen));
				} else {
					sw.setTrackResource(R.drawable.ic_track_unselected);
					r2.setTextColor(getResources().getColor(R.color.RequiredBlackTexts));
				}
			}
		});
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		confirmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				postChargebackRequest((TextView) v);

			}
		});
		/** Lock request is sent clicking on the padlock or on the message */
		lockImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				postLockRequest((ImageView) v, true);
			}
		});
		messageField.setOnClickListener(new OnClickListener() {
			ImageView image = (ImageView) findViewById(R.id.chargeback_padlock);

			@Override
			public void onClick(View v) {
				postLockRequest((ImageView) image, true);

			}
		});
		/* Creating ChargebackVO */
		chargeback = new ChargebackVO(jsonobject);
		String hint = chargeback.getCommentHint();
		hint = hint.replace("Transaction", title);
		String formatted = String.format(res.getString(R.string.formatted_html), hint);

		/** Setting Views */
		titleField.setText(chargeback.getTitle().toUpperCase(Locale.getDefault()));
		commentsField.setHint(Html.fromHtml(formatted));
		reason1Field.setText(chargeback.getReasonDetailsbyKey("merchant_recognized"));
		reason2Field.setText(chargeback.getReasonDetailsbyKey("card_in_possession"));
		confirmButton.setText(R.string.chargeback_confirm);
		cancelButton.setText(R.string.chargeback_cancel);

		/** Foreground transparency */
		FrameLayout frame = (FrameLayout) findViewById(R.id.chargeback_frame);
		frame.getForeground().setAlpha(0);

		/** Loading Layout */
		LayoutInflater l = getLayoutInflater();
		final View loader = l.inflate(R.layout.action_processing, null);
		popupLoader = new PopupWindow(loader, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		/**
		 * Send lock request if the autoblock message is sent from the server
		 */
		if (chargeback.isAutoblock()) {
			postLockRequest(lockImage, false);
		} else {
			messageField.setText(R.string.chargeback_card_unblocked);
			lockImage.setImageResource(R.drawable.ic_chargeback_unlock);
		}
	}

	/**
	 * Verifies if the message to be sent with the chargeback request is empty.
	 * if not, enable the button to submit the request
	 */
	protected void verifyEmptyField() {
		EditText msgField = (EditText) findViewById(R.id.chargeback_comments);
		TextView confirm = (TextView) this.findViewById(R.id.chargeback_confirm);
		if (!msgField.getEditableText().toString().trim().isEmpty()) {
			confirm.setTextColor(getResources().getColor(R.color.RequiredPurple));
			confirm.setEnabled(true);
		} else {
			confirm.setTextColor(getResources().getColor(R.color.RequiredDisabledGray));
			confirm.setEnabled(false);
		}
	}

	/**
	 * Send POST Request for the Chargeback Action
	 * 
	 * @param v
	 */
	protected void postChargebackRequest(TextView v) {

		showLoading();
		EditText commentsField = (EditText) this.findViewById(R.id.chargeback_comments);
		Switch primeSwitch = (Switch) this.findViewById(R.id.reason_button1);
		Switch secondSwitch = (Switch) this.findViewById(R.id.reason_button2);

		/* Switch status */
		Boolean sw1 = Boolean.parseBoolean(primeSwitch.getTag().toString());
		Boolean sw2 = Boolean.parseBoolean(secondSwitch.getTag().toString());

		/* JSON Object to send to the request */
		JSONObject param = new JSONObject();

		String comment = commentsField.getEditableText().toString();
		ArrayList<HashMap<String, Boolean>> reasonArray = new ArrayList<HashMap<String, Boolean>>();
		HashMap<String, Boolean> innerMap = new HashMap<String, Boolean>();
		innerMap.put("merchant_recognized", sw1);
		innerMap.put("card_in_possession", sw2);
		reasonArray.add(innerMap);
		param = ApplicationUtils.getJSONObject(comment, reasonArray);

		PostRequest post = new PostRequest(new OnComplete() {
			@Override
			public void onComplete(Boolean result) {
				dismissLoading();
				if (result) {
					showConfimationPopup();
				} else
					ApplicationUtils.showToastMessage(context, R.string.error_request, Toast.LENGTH_SHORT);
			}
		});
		if (param != null) {
			post.execute(chargeback.getLinksbyKey("self"), param.toString());
		} else
			ApplicationUtils.showToastMessage(context, R.string.error_request, Toast.LENGTH_SHORT);

	}

	/**
	 * send POST Request to lock/unlock card
	 * 
	 * @param image
	 */
	protected void postLockRequest(final ImageView image, final Boolean load) {
		final TextView lockMessage = (TextView) this.findViewById(R.id.chargeback_lock_message);
		if (load)
			showLoading();
		PostRequest post = new PostRequest(new OnComplete() {
			@Override
			public void onComplete(Boolean result) {
				if (load)
					dismissLoading();
				if (result) {
					chargeback.toggleStatus();

					ImageView img = image;
					TextView msg = lockMessage;
					if (chargeback.getBlockStatus()) {
						img.setImageResource(R.drawable.ic_chargeback_lock);
						msg.setText(R.string.chargeback_card_blocked);
					} else {
						img.setImageResource(R.drawable.ic_chargeback_unlock);
						msg.setText(R.string.chargeback_card_unblocked);
					}
				} else
					ApplicationUtils.showToastMessage(context, R.string.error_request, 10);
			}
		});
		if (!chargeback.getBlockStatus()) {
			post.execute(chargeback.getLinksbyKey("block_card"), new JSONObject().toString());
		} else {
			post.execute(chargeback.getLinksbyKey("unblock_card"), new JSONObject().toString());
		}

	}

	/**
	 * Show confirmation popup after the submission is sent and th OK is
	 * received from the server
	 */
	protected void showConfimationPopup() {

		LayoutInflater l = getLayoutInflater();
		final View confirmView = l.inflate(R.layout.chargeback_confirmation, null);
		TextView title = (TextView) confirmView.findViewById(R.id.chargeback_dialogue_title);
		TextView message = (TextView) confirmView.findViewById(R.id.chargeback_dialogue_message);
		TextView close = (TextView) confirmView.findViewById(R.id.chargeback_dialogue_close);
		title.setText(R.string.chargeback_confirm_title);
		message.setText(R.string.chargeback_confirm_message);
		close.setText(R.string.chargeback_confirm_close);
		final PopupWindow pop = new PopupWindow(confirmView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setForegroundAlpha(0);
				pop.dismiss();
				finish();
			}
		});
		setForegroundAlpha(200);
		/* Displaying the popup window */
		pop.showAtLocation(confirmView, Gravity.CENTER, 10, 10);
	}

	/**
	 * Foreground transparency
	 * 
	 * @param alpha
	 */
	protected void setForegroundAlpha(int alpha) {

		FrameLayout frame = (FrameLayout) findViewById(R.id.chargeback_frame);
		frame.getForeground().setAlpha(alpha);
	}

	protected void showLoading() {
		View loader = getLayoutInflater().inflate(R.layout.action_processing, null);
		if (popupLoader == null) {
			popupLoader = new PopupWindow(loader, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		}
		if (!popupLoader.isShowing()) {
			popupLoader = new PopupWindow(loader, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			setForegroundAlpha(100);
			/* Displaying the Loader */
			popupLoader.showAtLocation(loader, Gravity.CENTER, 10, 10);
		}
	}

	/**
	 * Dismiss the loading feature
	 */
	protected void dismissLoading() {
		if (popupLoader != null && popupLoader.isShowing()) {
			setForegroundAlpha(0);
			/* Dismiss the Loader */
			popupLoader.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chargeback, menu);
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
