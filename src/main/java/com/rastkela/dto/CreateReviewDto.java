package com.rastkela.dto;

import java.util.Optional;

public class CreateReviewDto {
    private int rating;
    private Optional<String> description;
    private Long userId;
    private Long gameId;

    public CreateReviewDto(){
    }

    public CreateReviewDto(int rating, Optional<String> description, Long userId, Long gameId) {
        this.rating = rating;
        this.description = description;
        this.userId = userId;
        this.gameId = gameId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}

