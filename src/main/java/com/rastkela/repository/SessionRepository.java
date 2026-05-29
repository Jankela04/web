package com.rastkela.repository;


import com.rastkela.model.Review;
import com.rastkela.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session,Long> {
    List<Session> findByUserId(Long userId);
    List<Session> findByGameId(Long gameId);
}
