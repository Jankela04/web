package com.rastkela.dto;

import com.rastkela.dto.game.GameDetailDto;

public class ReviewResponseDto {
    private Long id;
    private String description;
    private int rating;
    private UserDTO user;
    private GameDetailDto game;

    public ReviewResponseDto(Long id, String description, int rating, UserDTO user, GameDetailDto game) {
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.user = user;
        this.game = game;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public GameDetailDto getGame() {
        return game;
    }
    public void setGame(GameDetailDto game) {
        this.game = game;
    }
}
