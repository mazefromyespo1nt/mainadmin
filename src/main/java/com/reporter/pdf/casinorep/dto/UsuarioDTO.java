package com.reporter.pdf.casinorep.dto;

public class UsuarioDTO {
	 private String usuarioEmail;
	   private String password;
	    
	    public UsuarioDTO(String usuarioEmail, String password) {
			
			this.usuarioEmail = usuarioEmail;
			this.password = password;
	    }
	    public String getUsuarioEmail() {
	        return usuarioEmail;
	    }

	    public void setUsuarioEmail(String usuarioEmail) {
	        this.usuarioEmail = usuarioEmail;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }
	
	
	
}
