package com.rastkela.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rastkela.model.Achievement;
import com.rastkela.repository.AchievementRepository;

@Service
public class AchievementService {

    @Autowired
    private AchievementRepository achievementRepository;

    public Achievement findOne(Long achievementId){
        return achievementRepository.findById(achievementId).orElseThrow();
    }
    
}
