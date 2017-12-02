package com.fladev.model;

public class BsCall {
	private CallMethod method;
	private String uri;
	
	
	public BsCall(CallMethod method) {
		super();
		this.method = method;
	}


	public CallMethod getMethod() {
		return method;
	}


	public String getUri() {
		return uri;
	}


	public void setMethod(CallMethod method) {
		this.method = method;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
