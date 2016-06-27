package com.app.jobapplication.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

import android.util.Log;

/**
 * A helper to connect through HTTPS and acquire the information from the links
 * provided
 * 
 * @author Thais
 *
 */
public class ConnectionHelper {

	private static final String BASE_URL = "https://nu-mobile-hiring.herokuapp.com";
	private static final String ENCODING = "UTF-8";
	private static final String LOG = "ConnectionHelper";

	private static HttpsURLConnection getConnection(String endpoint) {
		try {
			URL url = new URL(endpoint);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			return connection;
		} catch (MalformedURLException e) {
			Log.e(LOG, "Malformed URL " + endpoint);
		} catch (IOException e) {
			Log.e(LOG, "Error Connecting to " + endpoint);
		}
		return null;
	}

	/**
	 * Gets the information from the BASE_URL
	 * 
	 * @return 
	 * SUCCESS: Request the information from the inner URL 
	 * FAILURE: Return null
	 */
	public static String getStream() {
		String url = BASE_URL;
		HttpsURLConnection connection = getConnection(url);
		String retrieved = "";
		if (connection != null) {
			InputStream messageStream = null;
			try {
				messageStream = connection.getInputStream();
				retrieved = IOUtils.toString(messageStream, ENCODING);

			} catch (IOException e) {
				Log.e(LOG, "Error on retrieving inputStream: " + connection.getErrorStream());
				return null;
			}
			String innerUrl = JsonParser.getInnerUrl(retrieved);
			return getFromEndpoint(innerUrl);
		}
		return null;
	}

	/**
	 * Requests information from the URL passed as a parameter
	 * 
	 * @param url
	 * @return 
	 * SUCCESS: Returns the string with the information retrieved from
	 * the server 
	 * FAILURE: Returns an empty string
	 */
	public static String getFromEndpoint(String url) {
		HttpsURLConnection connection = getConnection(url);
		if (connection != null) {
			String retrieved = "";
			InputStream messageStream = null;
			try {
				messageStream = connection.getInputStream();
				retrieved = IOUtils.toString(messageStream, ENCODING);
			} catch (IOException e) {
				Log.e(LOG, "Error on retrieving inputStream: " + connection.getErrorStream());
				return null;
			}

			return retrieved;
		}
		Log.e(LOG, "Unable to retrieve Input Stream");
		return "";
	}

	/**
	 * Post the chargeback and lock/unlock request When posting lock/unlock
	 * requests, the JSON object is empty
	 * 
	 * @param url
	 * @param json
	 * @return HTTP_OK: true ELSE: false
	 */
	public static boolean post(String url, String json) {
		HttpsURLConnection connection = getConnection(url);
		connection.setDoInput(true);
		connection.setDoOutput(true);

		try {

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			DataOutputStream outputstream = new DataOutputStream(connection.getOutputStream());
			OutputStreamWriter writer = new OutputStreamWriter(outputstream, ENCODING);
			writer.write(json);
			writer.flush();
			writer.close();
			int response = connection.getResponseCode();
			if (response == HttpsURLConnection.HTTP_OK) {
				Log.i(LOG, "OK");
				return true;

			} else {
				Log.e(LOG, connection.getResponseMessage());
				return false;
			}
		} catch (IOException e) {
			Log.e(LOG, "Error handing stream");
		}
		return false;
	}

}
