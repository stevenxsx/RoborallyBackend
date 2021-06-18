package com.example.demo.service.interfaces;

import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.admin.Game;
import com.example.demo.model.admin.User;

import java.util.Collection;
import java.util.List;


public interface IGameAdminService {

    List<Game> getGames() throws ServiceException, DaoException;

    Game getGame(int gameId) throws ServiceException, DaoException;

    void startGame(int gameId) throws ServiceException, DaoException;

    void endGame(int gameId) throws ServiceException, DaoException;

    int saveGame(Game game) throws ServiceException, DaoException;

    void deleteGame(Game game) throws ServiceException, DaoException;

    User validateUser(String name) throws ServiceException, DaoException;

    void addUser(User user) throws ServiceException, DaoException;

}
