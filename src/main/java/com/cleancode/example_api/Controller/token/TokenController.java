package com.cleancode.example_api.Controller.token;

import com.cleancode.example_api.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken() {
        String token = tokenService.generateToken();
        return ResponseEntity.ok(token);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestParam String token) {
        if (tokenService.validateToken(token)) {
            return ResponseEntity.ok("Authentication successful!");
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }
}
