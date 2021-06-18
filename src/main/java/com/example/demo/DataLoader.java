package com.example.demo;


import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.Player;
import com.example.demo.model.admin.Game;
import com.example.demo.model.admin.User;
import com.example.demo.service.interfaces.IGameAdminService;
import com.example.demo.service.interfaces.IGameService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The run method is executed upon startup, this can be used to do some data seeding.
 */
@Component
public class DataLoader implements ApplicationRunner {
    private final IGameAdminService gameAdminService;


    public DataLoader(IGameAdminService gameAdminService) {
        this.gameAdminService = gameAdminService;
    }

    public void createGame(String Name) throws ServiceException, DaoException {
        Game game = new Game(Name, -1, false);
        gameAdminService.saveGame(game);
        User user = new User(1, "Oline");
        User user2 = new User(2, "Mike");
        User user3 = new User(3, "Lucas");
        User user4 = new User(4, "Daniel");
        gameAdminService.addUser(user);
        gameAdminService.addUser(user2);
        gameAdminService.addUser(user3);
        gameAdminService.addUser(user4);
    }

    @Override
    public void run(ApplicationArguments args) throws ServiceException, DaoException {
        createGame("Nybegynder");
        createGame("Middelmådig");



    }
}
