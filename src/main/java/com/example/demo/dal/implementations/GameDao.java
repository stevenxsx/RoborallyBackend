package com.example.demo.dal.implementations;

import com.example.demo.model.Board;
import com.example.demo.dal.interfaces.IGameDao;
import com.example.demo.model.admin.Game;
import com.example.demo.model.admin.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;


@Repository
public class GameDao implements IGameDao {
    //BoardId, Board
    static final HashMap<Integer, Game> games = new HashMap<>();
    static private int gameIdCounter = 0;

    @Override
    public Collection<Game> getGames() {
        return games.values();
    }

    @Override
    public Game getGame(int gameId) {
        return games.get(gameId);
    }

    @Override
    public int createGame(Game game) {
        gameIdCounter++;
        game.setGameId(gameIdCounter);
        games.put(game.getGameId(), game);
        return gameIdCounter;
    }

    @Override
    public void deleteGame(int gameId) {
        games.remove(gameId);
    }

    @Override
    public void addUsers(int gameId, User user){
        Collection<Game> gameC = games.values();
        for(Game game : games.values()){
            if(game.getGameId() == gameId){
                game.getGameUsers().add(user);
            }
        }
    }
}