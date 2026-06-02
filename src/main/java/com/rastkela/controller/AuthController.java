package com.rastkela.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rastkela.dto.LoginDto;
import com.rastkela.dto.RegisterDto;
import com.rastkela.dto.UserDTO;
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
    
    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerData) {
        UserDTO newUser = authService.register(registerData);
        if(newUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully registered");
    }

    @PostMapping("/api/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");

        if(userSession == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        session.invalidate();
        
        return ResponseEntity.status(HttpStatus.OK).body("Successfully logged out");
    }
}
