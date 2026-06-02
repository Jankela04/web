package com.rastkela.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rastkela.dto.LoginDto;
import com.rastkela.dto.UserSession;
import com.rastkela.service.AuthService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto logindata, HttpSession session) {
        if(logindata.getUsername().isEmpty() || logindata.getPassword().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password is blank");

        UserSession userSession = authService.login(logindata);
        if(userSession == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
        }

        session.setAttribute("user", userSession);

        return ResponseEntity.ok("Successfully logged in!");
    }
    
    
}
