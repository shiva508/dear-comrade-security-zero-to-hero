package com.comrade.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {

	@PostMapping("/test")
	public ResponseEntity<?> welcomeTest() {
		Map<String, String> message=new HashMap<>();
		message.put("response", "Good Job!:)");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
