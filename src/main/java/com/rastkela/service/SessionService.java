package com.rastkela.service;

import com.rastkela.dto.SessionDTO;
import com.rastkela.dto.UserStatisticsDTO;
import com.rastkela.model.Session;
import com.rastkela.repository.GameRepository;
import com.rastkela.repository.SessionRepository;
import com.rastkela.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public SessionDTO findOne(Long id) {
        Session session = sessionRepository.findById(id).orElseThrow();
        return SessionDTO.fromEntity(session);
    }

    public List<SessionDTO> findAll() {
        return sessionRepository.findAll()
                .stream()
                .map(SessionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public SessionDTO create(Session session) {
        session.setStartDate(LocalDateTime.now());

        Session saved = sessionRepository.save(session);

        return SessionDTO.fromEntity(saved);
    }

    public SessionDTO end(Long id) {
        Session session = sessionRepository.findById(id).orElseThrow();

        session.setEndDate(LocalDateTime.now());

        Session saved = sessionRepository.save(session);

        return SessionDTO.fromEntity(saved);
    }

    public void delete(Long id) {
        sessionRepository.deleteById(id);
    }
    public UserStatisticsDTO getStatistics(Long userId) {

        List<Session> sessions = sessionRepository.findByUserId(userId);


        List<Session> finishedSessions = sessions.stream()
                .filter(s -> s.getEndDate() != null)
                .toList();


        long totalTime = finishedSessions.stream()
                .mapToLong(Session::getDurationInSeconds)
                .sum();


        Map<String, Long> playTimePerGame = finishedSessions.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getGame().getName(),
                        Collectors.summingLong(Session::getDurationInSeconds)
                ));


        Map<String, Long> launchesPerGame = finishedSessions.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getGame().getName(),
                        Collectors.counting()
                ));


        Map<String, Long> playTimePerCategory = finishedSessions.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getGame().getCategory().getName(),
                        Collectors.summingLong(Session::getDurationInSeconds)
                ));


        UserStatisticsDTO dto = new UserStatisticsDTO();

        dto.setTotalPlayTime(totalTime);
        dto.setPlayTimePerGame(playTimePerGame);
        dto.setLaunchesPerGame(launchesPerGame);
        dto.setPlayTimePerCategory(playTimePerCategory);

        return dto;
    }
}