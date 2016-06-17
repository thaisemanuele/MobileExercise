package com.app.jobapplication.chargebackexercise;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.util.Log;

public class ConnectionHelper {
	
	private static final String NOTICE_URL = "https://nu-mobile-hiring.herokuapp.com/notice";
	private static final String CHARGEBACK_URL = "https://nu-mobile-hiring.herokuapp.com/chargeback";
	private static final String BLOCKCARD_URL = "https://nu-mobile-hiring.herokuapp.com/card_block";
	private static final String UNBLOCKCARD_URL = "https://nu-mobile-hiring.herokuapp.com/card_unblock";
	
	private static final String LOG = "ConnectionHelper";
	
	private HttpsURLConnection getConnection(String endpoint){
		try {
			URL url = new URL(endpoint);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			return connection;
		} catch (MalformedURLException e) {
			Log.e(LOG, "Malformed URL "+endpoint);
		} catch (IOException e) {
			Log.e(LOG, "Error Connecting to "+endpoint);
		}
		return null;
	}
	
	public InputStream getNotice(){
		HttpsURLConnection connection = getConnection(NOTICE_URL);
		if(connection!=null){
			InputStream noticeStream = null;
			try {
				noticeStream = connection.getInputStream();
			} catch (IOException e) {
				Log.e(LOG, "Error on retrieving inputStream: "+connection.getErrorStream());
			}
			return noticeStream;
		}
		return null;	
	}
	
	public InputStream getChargeBack(){
		HttpsURLConnection connection = getConnection(CHARGEBACK_URL);
		if(connection!=null){
			InputStream chargebackStream = null;
			try {
				chargebackStream = connection.getInputStream();
			} catch (IOException e) {
				Log.e(LOG, "Error on retrieving inputStream: "+connection.getErrorStream());
			}
			
			return chargebackStream;
		}
		return null;	
	}

}
