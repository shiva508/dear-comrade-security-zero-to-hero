package com.comrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeController {
	@GetMapping("/")
	public String welcomePage() {
		return "main.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
}
