package com.rastkela.model;

import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "game_id"}
    )
)
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @Min(1)
    @Max(5)
    private int rating;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public Game getGame() {return game;}

    public void setGame(Game game) {this.game = game;}

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}
