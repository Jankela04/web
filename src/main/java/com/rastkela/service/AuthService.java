package com.rastkela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rastkela.dto.LoginDto;
import com.rastkela.dto.RegisterDto;
import com.rastkela.dto.UserDTO;
import com.rastkela.dto.UserSession;
import com.rastkela.enums.UserRole;
import com.rastkela.exception.UnauthorizedException;
import com.rastkela.model.User;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserSession login(LoginDto loginDto){
        User user = userService.findByUsername(loginDto.getUsername());
        if(user.isBlocked())
            throw new UnauthorizedException("User is banned");

        if(user == null ||
        !passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
        throw new UnauthorizedException("Wrong login credentials");

        return new UserSession(user.getId(),user.getUsername(),user.getEmail(),user.getRole());
    }

    public UserDTO register(RegisterDto registerDto){
        User user = new User();
        user.setBlocked(false);
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setBirthDate(registerDto.getBirthDate());
        user.setRole(UserRole.USER);
        user.setProfilePicture(registerDto.getProfilePicture());

        UserDTO newUser = userService.create(user);
        return newUser;
    }

    public boolean isLoggedIn(HttpSession session){
        return session.getAttribute("user") != null;
    }

    public UserSession getCurrentUser(HttpSession session){
        return (UserSession) session.getAttribute("user");
    }

    public boolean isAdmin(HttpSession session) {
        UserSession user = getCurrentUser(session);
        return user!= null && user.isAdmin();
    }

}
