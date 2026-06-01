package com.rastkela.dto.home.game;

public class GameDetailDto extends GameBasicDto {
    private String imgUrl;
    private String category;
    private double avgScore;

    public GameDetailDto() {
    }
    
    public GameDetailDto(Long id, String name, String imgUrl, String category, double avgScore) {
        super(id, name);
        this.imgUrl = imgUrl;
        this.category = category;
        this.avgScore = avgScore;
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
}