package com.app.jobapplication.chargebackexercise;

import java.io.InputStream;

/**
 * This interface is implemented by the class that needs a response from 
 * processes running in concurrency. Whenever a thread finishes and needs to 
 * send the results of its calculations, it will send those through the
 *  processFinish method.
 *
 * @author Thais Santos
 */

public interface AsyncResponse {

	void processFinish(String result);
}
