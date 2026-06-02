package com.rastkela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rastkela.dto.LoginDto;
import com.rastkela.dto.RegisterDto;
import com.rastkela.dto.UserDTO;
import com.rastkela.dto.UserSession;
import com.rastkela.enums.UserRole;
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

    public UserDTO register(RegisterDto registerDto){
        User user = new User();
        user.setBlocked(false);
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setBirthDate(registerDto.getBirthDate());
        user.setRole(UserRole.USER);
        user.setProfilePicture(registerDto.getProfilePicture());

        UserDTO newUser = userService.create(user);
        return newUser;
    }

}
