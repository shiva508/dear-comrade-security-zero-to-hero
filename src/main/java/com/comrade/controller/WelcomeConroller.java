package com.comrade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeConroller {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String hello() {
		return "welcome.html";
	}

	@GetMapping("/welcome")
	public String welcomePage() {
		return "welcome.html";
	}

	@PostMapping("/change")
	public String welcomePageChange() {
		return "welcome.html";
	}
}
