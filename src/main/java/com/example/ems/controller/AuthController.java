package com.example.ems.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data,
                                   HttpSession session) {

        String username = data.get("username");
        String password = data.get("password");

        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("loggedInUser", username);
            return ResponseEntity.ok().build();   // ✅ SUCCESS
        }

        return ResponseEntity.status(401).build(); // ❌ FAIL
    }
}
