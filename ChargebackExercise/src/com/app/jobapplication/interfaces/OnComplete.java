package com.app.jobapplication.interfaces;

/**
 * This interface is implemented by the class that needs a response from
 * processes running in concurrency. This one is used as a listener to show the
 * results of the Post Requests in the chargeback screen
 * 
 * @author Thais
 *
 */
public interface OnComplete {

	void onComplete(Boolean result);
}
