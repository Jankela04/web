package com.rastkela.dto.game;
import com.rastkela.model.Review;

import java.util.List;

public class GameBasicDto implements GameResponse {
    private Long id;
    private String name;
    private List<Review> reviews;

    public GameBasicDto() {
    }

    public GameBasicDto(Long id, String name, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.reviews = reviews;
    }

    public GameBasicDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}