package com.example.demo.dal.implementations;

import com.example.demo.dal.interfaces.IUserDao;
import com.example.demo.model.Board;
import com.example.demo.model.Space;
import com.example.demo.model.admin.Game;
import com.example.demo.model.admin.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class UserDao implements IUserDao {
    static final HashMap<String, User> users = new HashMap<>();

    @Override
    public Collection<User> getUsers() {
        return users.values();
    }

    @Override
    public void createUser(User user) {
        user.setPlayerId(user.playerId);
        user.setPlayerName(user.playerName);
        users.put(user.getPlayerName(),user);
    }

}
