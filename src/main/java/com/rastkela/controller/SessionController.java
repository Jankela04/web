package com.rastkela.controller;

import com.rastkela.dto.SessionDTO;
import com.rastkela.enums.UserRole;
import com.rastkela.model.Session;
import com.rastkela.model.User;
import com.rastkela.service.SessionService;
import com.rastkela.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<SessionDTO>> getAllSessions(HttpSession session) {

        User user = (User) session.getAttribute("user");

        boolean isAuthorised =
                user != null &&
                        user.getRole() == UserRole.ADMIN;

        if (!isAuthorised) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(sessionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDTO> getSession(
            @PathVariable Long id,
            HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");

        boolean isAuthorised =
                user != null &&
                        user.getRole() == UserRole.ADMIN;

        if (!isAuthorised) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(sessionService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<SessionDTO> create(
            @RequestBody Session session) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sessionService.create(session));
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<SessionDTO> end(
            @PathVariable Long id) {

        return ResponseEntity.ok(sessionService.end(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");

        boolean isAdmin =
                user != null &&
                        user.getRole() == UserRole.ADMIN;

        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        sessionService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

