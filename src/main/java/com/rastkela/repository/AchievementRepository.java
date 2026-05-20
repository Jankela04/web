package com.rastkela.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rastkela.model.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement,Long>{
    
}
