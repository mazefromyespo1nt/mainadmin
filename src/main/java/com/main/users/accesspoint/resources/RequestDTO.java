package com.main.users.accesspoint.resources;

public class RequestDTO<T> {

	private String request_type;
	private T request_body;
	
	public RequestDTO(String request_type, T request_body) {
		super();
		this.request_type = request_type;
		this.request_body = request_body;
	}

	public String getRequest_type() {
		return request_type;
	}

	public T getRequest_body() {
		return request_body;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public void setRequest_body(T request_body) {
		this.request_body = request_body;
	}
	
}
