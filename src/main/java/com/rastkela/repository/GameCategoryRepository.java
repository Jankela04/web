package com.rastkela.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rastkela.model.GameCategory;

public interface GameCategoryRepository extends JpaRepository<GameCategory,Long> {
    
}
