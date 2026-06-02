package com.rastkela.service;

import com.rastkela.model.Session;
import com.rastkela.repository.SessionRepository;
import com.rastkela.repository.GameRepository;
import com.rastkela.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;

    public Session findOne(Long id){
    return  sessionRepository.findById(id).orElseThrow();
    }
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Session create(Session session) {
        session.setStartDate(LocalDateTime.now());
        return sessionRepository.save(session);
    }

    public Session end(Long id) {
        Session session = sessionRepository.findById(id).orElseThrow();
        session.setEndDate(LocalDateTime.now());
        return sessionRepository.save(session);
    }

    public void delete(Long id) {
        sessionRepository.deleteById(id);
    }
}

