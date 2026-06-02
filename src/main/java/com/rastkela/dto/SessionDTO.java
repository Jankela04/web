package com.rastkela.dto;

import com.rastkela.model.Session;

import java.time.LocalDateTime;

public class SessionDTO {

    private Long id;
    private Long userId;
    private Long gameId;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Long durationInSeconds;

    public SessionDTO() {
    }

    public static SessionDTO fromEntity(Session session) {
        SessionDTO dto = new SessionDTO();

        dto.setId(session.getId());

        if (session.getUser() != null) {
            dto.setUserId(session.getUser().getId());
        }

        if (session.getGame() != null) {
            dto.setGameId(session.getGame().getId());
        }

        dto.setStartedAt(session.getStartDate());
        dto.setEndedAt(session.getEndDate());
        dto.setDurationInSeconds(session.getDurationInSeconds());

        return dto;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public Long getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public void setDurationInSeconds(Long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
}