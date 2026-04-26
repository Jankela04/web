package com.rastkela.model;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;
@Entity

public class Session implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "game_id")
        private Game game;

        @Column
        private LocalDate startDate;
        @Column
        private LocalDate endDate;
        @Column
        private Long durationInSeconds;

        public Long getId() {return id;}

        public Game getGame() {
                return game;
        }
        public LocalDate getStartDate() {return startDate;}

        public LocalDate getEndDate() {return endDate;}

        public Long getDurationInSeconds() {return durationInSeconds;}

        public void setId(Long id) {this.id = id;}

        public void setGame(Game game) {this.game = game;}

        public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

        public void setEndDate(LocalDate endDate) {this.endDate = endDate;}

        public void setDurationInSeconds(Long duration) {this.durationInSeconds = duration;}
}
