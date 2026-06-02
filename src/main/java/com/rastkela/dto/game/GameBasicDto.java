package com.rastkela.dto.game;
import com.rastkela.dto.ReviewResponseDto;

import java.util.List;

public class GameBasicDto implements GameResponse {
    private Long id;
    private String name;
    private List<ReviewResponseDto> reviews;

    public GameBasicDto() {
    }

    public GameBasicDto(Long id, String name, List<ReviewResponseDto> reviews) {
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

    public List<ReviewResponseDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewResponseDto> reviews) {
        this.reviews = reviews;
    }
}