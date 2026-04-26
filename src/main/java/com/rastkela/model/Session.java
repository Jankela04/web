package com.rastkela.model;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.*;
@Entity

public class Session implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private LocalDateTime endedAt;

    public Game getGame() {
        return game;
    }

    @Transient
    public Long getDurationInSeconds(){
        return Duration.between(startedAt, endedAt).getSeconds();
    }
    public Long getId() { return this.id;}

    public LocalDateTime getStartDate() {return startedAt;}

    public LocalDateTime getEndDate() {return endedAt;}

    public void setId(Long id) {this.id = id;}

    public void setGame(Game game) {this.game = game;}

    public void setStartDate(LocalDateTime startDate) {this.startedAt = startDate;}

    public void setEndDate(LocalDateTime endDate) {this.endedAt = endDate;}
}
