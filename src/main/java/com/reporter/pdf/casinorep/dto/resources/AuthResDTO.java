package com.reporter.pdf.casinorep.dto.resources;

public class AuthResDTO {

	private String token_key;
	private String message;
	
	public AuthResDTO(String token_key, String message) {
		super();
		this.token_key = token_key;
		this.message = message;
	}
	
	public AuthResDTO() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getToken_key() {
		return token_key;
	}
	
	public void setToken_key(String token_key) {
		this.token_key = token_key;
	}
	
}
