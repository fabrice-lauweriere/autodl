package com.fladev.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fladev.exception.AutoDlException;
import com.fladev.model.BsCall;
import com.fladev.model.Constants;

public class CallExecUtils {
	
	
	public static String executeCall(BsCall call) {
		StringBuilder jsonResp = new StringBuilder(Constants.EMPTY_STRING);
	    try {
			URL url = new URL(call.getUri());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//Setting the Request Method header
			conn.setRequestMethod(call.getMethod().toString());
			//Setting the Content Type Header as application/json
			conn.setRequestProperty("Content-Type", "application/json");
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String line = "";
			while ((line = br.readLine()) != null) {
				jsonResp.append(line);
			}
		} catch (MalformedURLException e) {
			throw new AutoDlException("Provided URL is malformed : " + call.getUri(), e);
		} catch (IOException e) {
			throw new AutoDlException("Error during connexion to : " + call.getUri(), e);
		}
		
		return jsonResp.toString();
	}
}
