package com.app.jobapplication.chargebackexercise;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

import android.util.Log;

public class ConnectionHelper {
	
	private static final String BASE_URL = "https://nu-mobile-hiring.herokuapp.com";
	private static final String NOTICE_URL = "https://nu-mobile-hiring.herokuapp.com/notice";
	
	private static final String ENCODING = "UTF-8";
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
	
	public String getFromEndpoint(String url){
		if(url.isEmpty()){
			url = NOTICE_URL;
		}
		HttpsURLConnection connection = getConnection(url);
		if(connection!=null){
			String messageString = "";
			InputStream messageStream = null;
			try {
				messageStream = connection.getInputStream();
				messageString = IOUtils.toString(messageStream, ENCODING);
			} catch (IOException e) {
				Log.e(LOG, "Error on retrieving inputStream: "+connection.getErrorStream());
			}
			return messageString;
		}
		return "";
	}
	
}
