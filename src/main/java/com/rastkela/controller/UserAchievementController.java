package com.rastkela.controller;

import com.rastkela.model.User;
import com.rastkela.model.UserAchievement;
import com.rastkela.service.UserAchievementService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAchievementController {

    @Autowired
    private UserAchievementService userAchievementService;

    @GetMapping("/me/achievements")
    public ResponseEntity<List<UserAchievement>> getMyAchievements(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(
                userAchievementService.findByUser(user.getId())
        );
    }
}