package com.rastkela.dto.game;

import java.util.List;

import com.rastkela.model.Review;

public class GameDetailDto extends GameBasicDto {
    private String imgUrl;
    private String category;
    private double avgScore;
    private String gameUrl;
    private List<Review> reviews;

    public GameDetailDto() {
    }

    public GameDetailDto(Long id, String name, String imgUrl, String category, double avgScore, String gameUrl, List<Review> reviews) {
        super(id, name);
        this.imgUrl = imgUrl;
        this.category = category;
        this.avgScore = avgScore;
        this.gameUrl = gameUrl;
        this.reviews = reviews;
    }

    public GameDetailDto(Long id, String name, String imgUrl, String category, double avgScore, String gameUrl) {
        super(id, name);
        this.imgUrl = imgUrl;
        this.category = category;
        this.avgScore = avgScore;
        this.gameUrl = gameUrl;
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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
     
}