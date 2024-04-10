package com.reporter.pdf.casinorep.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reporter.pdf.casinorep.dto.RegistroDTO;
import com.reporter.pdf.casinorep.dto.UsuarioDTO;


	
	@RestController
	public class UsuarioController {
		
		 @PostMapping("/registro")
		    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroRequest) {
		        String nombreValido = "kurko";
		        String apellidoValido = "bein";
		        String correoValido = "kurko@hotmail.com";
		        String contraseñaValida = "contraseña123";

		        if (nombreValido.equals(registroRequest.getNombre()) &&
		                apellidoValido.equals(registroRequest.getApellido()) &&
		                correoValido.equals(registroRequest.getCorreo()) &&
		                contraseñaValida.equals(registroRequest.getContraseña())) {
		            System.out.println("Registro exitoso!");
		            return ResponseEntity.ok("Usuario registrado correctamente");
		        } else {
		            System.out.println("Registro fallido. Verifica tus credenciales de registro.");
		            return ResponseEntity.badRequest().body("Registro fallido. Verifica tus credenciales de registro.");
		        }
		    }
		
		@PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@RequestBody UsuarioDTO loginRequest) {

	// Autenticar el usuario utilizando el AuthenticationManager
	// Authentication authentication = authenticationManager.authenticate(
	// new UsernamePasswordAuthenticationToken(
	// loginRequest.usuarioEmail(), // Obtener el nombre de usuario o correo electrónico del cuerpo de la solicitud
	// loginRequest.getPassword() // Obtener lgetUsuarioEmaila contraseña del cuerpo de la solicitud
//	                )
//	                
//	        );
			
			
			
			
	        	String username = "Danna24";
	        	String password = "nanchasysorullo";
	        	
	        	if(username.equals(loginRequest.getUsuarioEmail()) && password.equals(loginRequest.getPassword())) {
	                System.out.println("Acertaste!");
	                return ResponseEntity.ok(new UsuarioDTO(username, password));
	            } else {
	                System.out.println("Intentalo de nuevo!");
	                return ResponseEntity.badRequest().build();
	            }        	
	        }
	        	
	        // Establecer la autenticación en el contexto de seguridad
	       // SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Generar un token JWT utilizando el JwtTokenProvider
	       // String jwt = jwtTokenProvider.generateToken(authentication);

	        // Devolver la respuesta
	       // return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	    }


