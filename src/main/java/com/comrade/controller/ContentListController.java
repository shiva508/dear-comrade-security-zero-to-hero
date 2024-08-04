package com.comrade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/content")
public class ContentListController {
    @GetMapping("/details")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Spring Security ", HttpStatus.OK);
    }
}
