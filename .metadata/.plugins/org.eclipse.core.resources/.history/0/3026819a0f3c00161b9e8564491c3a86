package com.app.jobapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class ConnectionDetector {
	
	private Context context;
	
	public ConnectionDetector(Context context){
		this.context = context;
	}
	
	/**
	 * Check whether the device is somehow connected
	 * @return true/false
	 */
	public Boolean isConnected() {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (!(cm == null)) {
			Network[] networks = cm.getAllNetworks();
			for (Network net : networks) {
				NetworkInfo netInfo = cm.getNetworkInfo(net);
				if (!(netInfo == null)) {
					if (netInfo.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
