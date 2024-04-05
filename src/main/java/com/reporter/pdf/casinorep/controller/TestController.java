package com.reporter.pdf.casinorep.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reporter.pdf.casinorep.dto.resources.LogInDTO;
import com.reporter.pdf.casinorep.dto.resources.RequestDTO;
import com.reporter.pdf.casinorep.dto.resources.ResponseDTO;

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
	
}
