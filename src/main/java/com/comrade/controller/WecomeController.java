package com.comrade.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WecomeController {
	@GetMapping("/wecome")
	public String welcome() {
		return "welcome";
	}
}
