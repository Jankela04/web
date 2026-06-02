package com.rastkela.dto;

import java.util.Map;

public class UserStatisticsDTO {

    private Long totalPlayTime;

    private Map<String, Long> playTimePerGame;

    private Map<String, Long> launchesPerGame;

    private Map<String, Long> playTimePerCategory;

    public UserStatisticsDTO() {
    }

    public Long getTotalPlayTime() {
        return totalPlayTime;
    }

    public void setTotalPlayTime(Long totalPlayTime) {
        this.totalPlayTime = totalPlayTime;
    }

    public Map<String, Long> getPlayTimePerGame() {
        return playTimePerGame;
    }

    public void setPlayTimePerGame(Map<String, Long> playTimePerGame) {
        this.playTimePerGame = playTimePerGame;
    }

    public Map<String, Long> getLaunchesPerGame() {
        return launchesPerGame;
    }

    public void setLaunchesPerGame(Map<String, Long> launchesPerGame) {
        this.launchesPerGame = launchesPerGame;
    }

    public Map<String, Long> getPlayTimePerCategory() {
        return playTimePerCategory;
    }

    public void setPlayTimePerCategory(Map<String, Long> playTimePerCategory) {
        this.playTimePerCategory = playTimePerCategory;
    }
}