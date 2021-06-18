package com.example.demo.controller.GameController;

public class UserDTO {

    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        this.playerName = name;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    private String playerName;
    private Integer playerId;
}
