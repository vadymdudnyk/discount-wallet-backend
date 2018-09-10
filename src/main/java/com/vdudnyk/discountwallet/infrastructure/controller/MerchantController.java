package com.vdudnyk.discountwallet.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/merchant")
public class MerchantController {
    @PostMapping()
    ResponseEntity<String> test() {
        return ResponseEntity.ok("SUCCESS");
    }
}
