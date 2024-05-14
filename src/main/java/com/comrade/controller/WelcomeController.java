package com.comrade.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public Map<String,String> welcome(){
        return Map.of("message","welcome");
    }
}
