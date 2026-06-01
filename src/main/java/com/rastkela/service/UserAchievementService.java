package com.rastkela.service;

import com.rastkela.model.UserAchievement;
import com.rastkela.repository.UserAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAchievementService {

    @Autowired
    private UserAchievementRepository userAchievementRepository;

    public UserAchievement findOne(Long id) {
        return userAchievementRepository.findById(id).orElseThrow();
    }

    public List<UserAchievement> findAll() {
        return userAchievementRepository.findAll();
    }

    public List<UserAchievement> findByUser(Long userId) {
        return userAchievementRepository.findByUserId(userId);
    }

    public List<UserAchievement> findByGame(Long gameId) {
        return userAchievementRepository.findByGameId(gameId);
    }

    public List<UserAchievement> findByAchievement(Long achievementId) {
        return userAchievementRepository.findByAchievementId(achievementId);
    }

    public UserAchievement create(UserAchievement userAchievement) {
        return userAchievementRepository.save(userAchievement);
    }

    public void delete(Long id) {
        userAchievementRepository.deleteById(id);
    }
}