package com.rastkela.repository;

import com.rastkela.model.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAchievementRepository extends JpaRepository<UserAchievement,Long> {
    List<UserAchievement> findByGameId(Long gameId);
    List<UserAchievement> findByUserId(Long userId);
    List<UserAchievement> findByAchievementId(Long achievementId);
}
