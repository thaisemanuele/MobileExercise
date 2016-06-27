package com.app.jobapplication.activities;

import java.util.List;

import com.app.jobapplication.chargebackexercise.R;
import com.app.jobapplication.factory.TransactionFactory;
import com.app.jobapplication.helper.TransactionListAdapter;
import com.app.jobapplication.models.Transaction;
import com.app.jobapplication.utils.ApplicationUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

/**
 * MainActivity
 * Displays a list of simulated transactions, 
 * allowing the user to ask for a chargeback
 * @author Thais
 *
 */
public class MainActivity extends Activity {

	private Context context;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = getApplicationContext();
		/*Checking the device's connection*/
		final Boolean connected = ApplicationUtils.checkConnection(context);
		if(!connected){
			ApplicationUtils.showToastMessage(context, R.string.error_not_connected, Toast.LENGTH_SHORT);
		}
		/*Showing the List of transactions*/
		List<Transaction> transactions = TransactionFactory.getList();
		List<String> titles = TransactionFactory.getTitles();
		TransactionListAdapter adapter;
        adapter = new TransactionListAdapter(MainActivity.this, R.layout.transaction_row,
        			R.id.transaction_list, transactions, titles);
        ListView transactionList = (ListView)findViewById(R.id.transaction_list);
        transactionList.setAdapter(adapter);
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
		if (id == R.id.open_about) {
			ApplicationUtils.openAbout(MainActivity.this);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
