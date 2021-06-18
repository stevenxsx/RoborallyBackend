package com.example.demo.model.admin;

public class User {
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int playerId;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String playerName;

    public User(int playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }
}
