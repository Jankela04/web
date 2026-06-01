package com.rastkela.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rastkela.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByGameId(Long gameId);

    List<Review> findByUserId(Long userId);

    Review findByGameIdAndUserId(Long gameId, Long userId);

    boolean existsByGameIdAndUserId(Long gameId, Long userId);

    @Query("""
                SELECT r.game.id, COALESCE(AVG(r.rating), 0)
                FROM Review r
                GROUP BY r.game.id
            """)
    List<Object[]> findAverageScores();
}
