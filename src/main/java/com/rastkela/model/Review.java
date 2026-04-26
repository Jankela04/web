package com.rastkela.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.rastkela.enums.ReviewRating;
import jakarta.persistence.*;
@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String title;
    @Column(length = 250)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
    @Enumerated(EnumType.STRING)
    private ReviewRating rating;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public Game getGame() {return game;}

    public void setGame(Game game) {this.game = game;}

    public ReviewRating getRating() {
        return rating;
    }

    public void setRating(ReviewRating rating) {
        this.rating = rating;
    }


}
