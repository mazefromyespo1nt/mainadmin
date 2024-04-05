package com.reporter.pdf.casinorep.dto.resources;

public class ResponseDTO<T> {

	private String response_message;
	private String response_status;
	private T response_body;
	
	public ResponseDTO(String response_message,String response_status, T response_body) {
		super();
		this.response_message = response_message;
		this.response_status = response_status;
		this.response_body = response_body;
	}


	public T getResponse_body() {
		return response_body;
	}

	public String getResponse_status() {
		return response_status;
	}

	public String getResponse_message() {
		return response_message;
	}


	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}


	public void setResponse_status(String response_status) {
		this.response_status = response_status;
	}


	public void setResponse_body(T response_body) {
		this.response_body = response_body;
	}
	
}
