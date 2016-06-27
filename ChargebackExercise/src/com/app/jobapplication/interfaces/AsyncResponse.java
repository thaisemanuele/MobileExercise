package com.app.jobapplication.interfaces;

/**
 * This interface is implemented by the class that needs a response from
 * processes running in concurrency. Whenever a thread finishes and needs to
 * send the results of its calculations(Boolean or String Results), it will send
 * those through the processFinish method.
 *
 * @author Thais Santos
 */

public interface AsyncResponse {

	void processFinish(String result);
	void processFinish(Boolean result);
}
