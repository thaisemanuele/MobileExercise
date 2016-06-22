package com.app.jobapplication.activities;

import com.app.jobapplication.chargebackexercise.NoticeStarter;
import com.app.jobapplication.chargebackexercise.R;
import com.app.jobapplication.utils.ApplicationUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Context context;
	private PopupWindow popupLoader;
	private final int activityFrame = R.id.container;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = getApplicationContext();
		popupLoader = new PopupWindow();
		
		final Boolean connected = ApplicationUtils.checkConnection(context);
		if(!connected){
			ApplicationUtils.showToastMessage(context, R.string.error_not_connected, Toast.LENGTH_SHORT);
		}

		Button start = (Button)findViewById(R.id.start_button);
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ApplicationUtils.checkConnection(context)){
					NoticeStarter message = new NoticeStarter();
					message.startNotice("", MainActivity.this);
				}
				else {
					ApplicationUtils.showToastMessage(context, R.string.error_not_connected, 10);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
