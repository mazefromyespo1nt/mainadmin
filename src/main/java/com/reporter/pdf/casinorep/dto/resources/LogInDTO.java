package com.reporter.pdf.casinorep.dto.resources;

public class LogInDTO {

	private String user_value;
	private String user_key;
	
	public LogInDTO(String user_value, String user_key) {
		super();
		this.user_value = user_value;
		this.user_key = user_key;
	}
	
	public String getUser_value() {
		return user_value;
	}
	
	public String getUser_key() {
		return user_key;
	}

	public void setUser_value(String user_value) {
		this.user_value = user_value;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	
	
	
}
