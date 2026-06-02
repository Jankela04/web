package com.rastkela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rastkela.dto.LoginDto;
import com.rastkela.dto.UserSession;
import com.rastkela.model.User;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public UserSession login(LoginDto loginDto){
        User user = userService.findByUsername(loginDto.getUsername());
        if(user == null || !user.getPassword().equals(loginDto.getPassword()))
            return null;

        return new UserSession(user.getId(),user.getUsername(),user.getEmail(),user.getRole());
    }

}
