package com.rastkela.dto;

import java.util.List;

import com.rastkela.dto.game.GameBasicDto;

public class HomeResponse {
    private long playerCount;
    private long gameCount;
    private List<? extends GameBasicDto> games;

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

    public List<? extends GameBasicDto> getGames() {
        return games;
    }

    public void setGames(List<? extends GameBasicDto> games) {
        this.games = games;
    }
}
