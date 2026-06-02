package com.rastkela.dto.game;

import java.util.List;

import com.rastkela.dto.ReviewResponseDto;
import com.rastkela.model.Review;

public class GameDetailDto extends GameBasicDto {
    private String imgUrl;
    private String category;
    private double avgScore;
    private String gameUrl;
    boolean active;
    private String description;

    public GameDetailDto() {
    }

    public GameDetailDto(Long id, String name, String imgUrl, String category, double avgScore, String gameUrl, List<ReviewResponseDto> reviews, boolean active, String description) {
        super(id, name,reviews);
        this.imgUrl = imgUrl;
        this.category = category;
        this.avgScore = avgScore;
        this.gameUrl = gameUrl;
        this.active = active;
        this.description = description;
    }

    public GameDetailDto(Long id, String name, String imgUrl, String category, double avgScore, String gameUrl, boolean active,String description) {
        super(id, name);
        this.imgUrl = imgUrl;
        this.category = category;
        this.avgScore = avgScore;
        this.gameUrl = gameUrl;
        this.active = active;
        this.description = description;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean activated) {
        this.active = activated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}