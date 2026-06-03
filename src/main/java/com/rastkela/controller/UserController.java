package com.rastkela.controller;

import com.rastkela.dto.SessionDTO;
import com.rastkela.dto.UserSession;
import com.rastkela.dto.UserStatisticsDTO;
import com.rastkela.service.SessionService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/me")
    public ResponseEntity<UserSession> getMe(HttpSession httpSession) {

        UserSession user =
                (UserSession) httpSession.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/me/sessions")
    public ResponseEntity<List<SessionDTO>> getMySessions(HttpSession httpSession) {

        UserSession user =
                (UserSession) httpSession.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(
                sessionService.findByUser(user.getId())
        );
    }

    @GetMapping("/me/statistics")
    public ResponseEntity<UserStatisticsDTO> getMyStats(HttpSession httpSession) {

        UserSession user =
                (UserSession) httpSession.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(
                sessionService.getStatistics(user.getId())
        );
    }
}