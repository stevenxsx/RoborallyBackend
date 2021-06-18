package com.example.demo.service.implementations;

import com.example.demo.dal.interfaces.IGameDao;
import com.example.demo.dal.interfaces.IUserDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.model.admin.Game;
import com.example.demo.model.admin.User;
import com.example.demo.service.interfaces.IGameAdminService;
import com.example.demo.service.interfaces.IGameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GameAdminService implements IGameAdminService {

    private final IGameDao gameDao;
    private final IGameService gameService;
    private final IUserDao userDao;

    public GameAdminService(IGameDao gameDao, IGameService gameService, IUserDao userDao) {
        this.gameDao = gameDao;
        this.gameService = gameService;
        this.userDao = userDao;
    }

    @Override
    public List<Game> getGames() throws ServiceException, DaoException {
        Collection<Game> gamesMap = gameDao.getGames();
        return new ArrayList<>(gamesMap);
    }

    @Override
    public Game getGame(int gameId) throws DaoException {
        return gameDao.getGame(gameId);
    }

    @Override
    public void startGame(int gameId) throws ServiceException, DaoException {
        gameDao.getGame(gameId).setGameStarted(true);
    }

    @Override
    public void endGame(int gameId) throws ServiceException, DaoException {
        gameDao.getGame(gameId).setGameStarted(false);
    }

    @Override
    public int saveGame(Game game) throws ServiceException, DaoException {
        int savedGameId = gameDao.createGame(game);
        Board board = new Board(8, 8, game.gameName);
        int boardId = gameService.saveBoard(board);
        Player player = new Player(board);
        gameService.addPlayer(boardId, player);
        gameService.setCurrentPlayer(boardId, player.getPlayerId());
        gameService.moveCurrentPlayer(boardId, 5, 5);
        Player player2 = new Player(board);
        gameService.addPlayer(boardId, player2);
        gameService.setCurrentPlayer(boardId, player2.getPlayerId());
        gameService.moveCurrentPlayer(boardId, 1, 1);
        if (savedGameId < 0) {
            throw new ServiceException("GameDao generated invalid gameId " + savedGameId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return savedGameId;
    }

    @Override
    public void deleteGame(Game game) throws ServiceException, DaoException {
        gameDao.deleteGame(game.getGameId());
    }
    @Override
    public User validateUser (String name) throws ServiceException, DaoException{
        for(User user: userDao.getUsers()){
            if(user.playerName.equals(name)){
                return user;
            }
        }
        return null;
    }
    @Override
    public void addUser(User user) throws ServiceException, DaoException{
        userDao.createUser(user);
    }

    @Override
    public Collection<User> getUsers() throws ServiceException, DaoException{
        return userDao.getUsers();
    }

}
