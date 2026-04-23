package com.rastkela.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "game_id", "achievement_id"}
    )
)
public class UserAchievement implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user; 

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game; 

    @ManyToOne(fetch = FetchType.EAGER)
    private Achievement achievement;

    public User getUser() {
        return user;
    }

    public Game getGame() {
        return game;
    }

    public Achievement getAchievement() {
        return achievement;
    } 
    
}
