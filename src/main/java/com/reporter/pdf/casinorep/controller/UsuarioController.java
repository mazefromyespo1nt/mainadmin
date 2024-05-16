package com.reporter.pdf.casinorep.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reporter.pdf.casinorep.dto.RegistroDTO;
import com.reporter.pdf.casinorep.dto.UsuarioDTO;


	
	@RestController // Esto es una clase e indica es un controlador REST que maneja solicitudes HTTP
public class UsuarioController {
    
    // Este es un metodo para manejar solicitudes POST en la ruta "/registro" pal postmalone
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroRequest) {
        // Datos que son validos para comparar
        String nombreValido = "kurko";
        String apellidoValido = "bein";
        String correoValido = "kurko@hotmail.com";
        String contrasenaValida = "contraseña123";

        // Esto verifica si los datos enviados en el cuerpo de la solicitud coinciden con los datos validos que pusimos anteriormente
        if (nombreValido.equals(registroRequest.getNombre()) &&
                apellidoValido.equals(registroRequest.getApellido()) &&
                correoValido.equals(registroRequest.getCorreo()) &&
                contrasenaValida.equals(registroRequest.getContraseña())) {
            // Si los datos coinciden, se imprime un mensaje de registro exitoso y se devuelve una respuesta exitosa el cual es (código 200)
            System.out.println("Registro exitoso!");
            return ResponseEntity.ok("Usuario registrado correctamente");
        } else {
            // Si los datos no coinciden, se imprime un mensaje de registro fallido y se devuelve una respuesta de error que es el (código 400)
            System.out.println("Registro fallido. Verifica tus credenciales de registro.");
            return ResponseEntity.badRequest().body("Registro fallido. Verifica tus credenciales de registro.");
        }
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
			
			
			
			// Credenciales validas para la autenticacion
	        	String username = "Danna24";
	        	String password = "nanchasysorullo";
	        	
				    // Verifica si las credenciales enviadas en el cuerpo de la solicitud coinciden con las credenciales que pusimos anteriormente
	        	if(username.equals(loginRequest.getUsuarioEmail()) && password.equals(loginRequest.getPassword())) {
				   // Si las credenciales coinciden, se imprime un mensaje de acierto y se devuelve una respuesta exitosa con los detalles del usuario autenticado
	                System.out.println("Acertaste!");
	                return ResponseEntity.ok(new UsuarioDTO(username, password));
	            } else {
					// Si las credenciales no coinciden, se imprime un mensaje para intentar de nuevo y se devuelve una respuesta de error que es el (código 400)	
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


