package com.fladev.model;

public enum CallMethod {
	GET("GET"),
	
	POST("POST");
	
	private String method;

	private CallMethod(String method) {
		this.method = method;
	}
	
	
}
