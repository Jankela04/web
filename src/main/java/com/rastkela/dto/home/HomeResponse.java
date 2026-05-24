package com.rastkela.dto.home;

import java.util.List;

public class HomeResponse {
    private long playerCount;
    private long gameCount;
    private List<? extends GameBasic> games;

    public HomeResponse() {
    }

    public long getPlayerCount() {
        return playerCount;
    }
    public void setPlayerCount(long playerCount) {
        this.playerCount = playerCount;
    }
    public long getGameCount() {
        return gameCount;
    }
    public void setGameCount(long gameCount) {
        this.gameCount = gameCount;
    }
    public List<? extends GameBasic> getGames() {
        return games;
    }
    public void setGames(List<? extends GameBasic> games) {
        this.games = games;
    }
}

