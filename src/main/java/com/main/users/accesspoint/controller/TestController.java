package com.main.users.accesspoint.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.users.accesspoint.resources.AuthResDTO;
import com.main.users.accesspoint.resources.LogInDTO;
import com.main.users.accesspoint.resources.RequestDTO;
import com.main.users.accesspoint.resources.ResponseDTO;

@RestController
public class TestController {

	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("/testuno")
	String testGet() {
		return "Hello world!!";
	}
	
	@GetMapping("/testdos")
	String testGetParams(@RequestParam String dato_prueba, @RequestParam String dato_test) {
		return "Hello world!! dato prueba: "+dato_prueba+"dato test: "+dato_test;
	}
	
	@PostMapping("/testtres")
	String testPostBody(@RequestBody Map<String, Object> reqBody) {
		reqBody.keySet().forEach(key -> {

			logger.debug("Post TEST key: {} value: {}", key, reqBody.get(key));
		});
		return "Hello post!!";
	}
	
	@PostMapping("/testcuatro")
	ResponseDTO<Map<String, Object>> testPostBodySpecific(@RequestBody RequestDTO<LogInDTO> reqBody) {

		logger.debug("Post TEST type: {}", reqBody.getRequest_type());

		logger.debug("Post TEST name: {}", reqBody.getRequest_body().getUser_value());

		logger.debug("Post TEST key: {}", reqBody.getRequest_body().getUser_key());

		Map<String, Object> response_data = new HashMap<String, Object>();

		response_data.put("message", "logged-in"); 
		response_data.put("token_key", "ndkjdhfadd0q3980q923849q3r"); 
		//ResponseDTO<Map<String, Object>> responseBody = new ResponseDTO<Map<String, Object>>("O0", "se procesa peticion", response_data);
		return new ResponseDTO<Map<String, Object>>("O0", "se procesa peticion", response_data);

	}
	
	@PostMapping("/testcinco")
	ResponseEntity<ResponseDTO<Map<String, Object>>> testPostBodySpecificResEntity(@RequestBody RequestDTO<LogInDTO> reqBody) {

		logger.debug("Post TEST type: {}", reqBody.getRequest_type());

		logger.debug("Post TEST name: {}", reqBody.getRequest_body().getUser_value());

		logger.debug("Post TEST key: {}", reqBody.getRequest_body().getUser_key());

		Map<String, Object> response_data = new HashMap<String, Object>();

		if(reqBody.getRequest_body().getUser_value().equals("chuy13") && reqBody.getRequest_body().getUser_key().equals("p3rR1t4T")) {

			response_data.put("message", "logged-in"); 
			response_data.put("token_key", "ndkjdhfadd0q3980q923849q3r"); 
			//ResponseDTO<Map<String, Object>> responseBody = new ResponseDTO<Map<String, Object>>("O0", "se procesa peticion", response_data);

			//return ResponseEntity.badRequest().build();
			return ResponseEntity.ok(new ResponseDTO<Map<String, Object>>("O0", "se procesa peticion", response_data));
		} else {
			response_data.put("message", "logged-fail"); 
			response_data.put("token_key", "fail?token?generation"); 
			return ResponseEntity.badRequest().body(new ResponseDTO<Map<String, Object>>("O1", "no se procesa peticion", response_data));
		}
		

	}
	
	
	@PostMapping("/testseis")
	ResponseEntity<ResponseDTO<AuthResDTO>> testPostBodySpecificResEntityClass(@RequestBody RequestDTO<LogInDTO> reqBody) {

		logger.debug("Post TEST type: {}", reqBody.getRequest_type());

		logger.debug("Post TEST name: {}", reqBody.getRequest_body().getUser_value());

		logger.debug("Post TEST key: {}", reqBody.getRequest_body().getUser_key());

		AuthResDTO response_data = new AuthResDTO();

		if(reqBody.getRequest_body().getUser_value().equals("chuy13") && reqBody.getRequest_body().getUser_key().equals("p3rR1t4T")) {

			response_data.setMessage("logged-in"); 
			response_data.setToken_key("ndkjdhfadd0q3980q923849q3r"); 
			//ResponseDTO<Map<String, Object>> responseBody = new ResponseDTO<Map<String, Object>>("O0", "se procesa peticion", response_data);

			//return ResponseEntity.badRequest().build();
			return ResponseEntity.ok(new ResponseDTO<AuthResDTO>("O0", "se procesa peticion", response_data));
		} else {
			response_data.setMessage("logged-fail"); 
			response_data.setToken_key("fail?token?generation"); 
			return ResponseEntity.badRequest().body(new ResponseDTO<AuthResDTO>("O1", "no se procesa peticion", response_data));
		}
		

	}
}
