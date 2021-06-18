package com.example.demo.gameAdminController;

import com.example.demo.controller.GameController.BoardDto;
import com.example.demo.controller.GameController.GameDto;
import com.example.demo.controller.GameController.UserDTO;
import com.example.demo.dal.interfaces.IUserDao;
import com.example.demo.exceptions.DaoException;
import com.example.demo.exceptions.MappingException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.model.Board;
import com.example.demo.model.admin.Game;
import com.example.demo.model.admin.User;
import com.example.demo.service.interfaces.IGameAdminService;
import com.example.demo.util.mapping.IDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GameAdminController {
    /**
     * @author s205444, Lucas
     */

    private final IGameAdminService gameAdminService;
    private final IDtoMapper dtoMapper;


    public GameAdminController(IGameAdminService gameAdminService, IDtoMapper dtoMapper) {
        this.gameAdminService = gameAdminService;
        this.dtoMapper = dtoMapper;
    }


    @GetMapping("/games")
    public ResponseEntity<List<Game>> getGames() throws ServiceException, MappingException, DaoException {
        return new ResponseEntity<>(gameAdminService.getGames(), HttpStatus.OK);
    }


    @GetMapping("/game/{gameId}")
    public ResponseEntity<GameDto> getGame(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
        Game game = gameAdminService.getGame(gameId);
        return new ResponseEntity<>(dtoMapper.convertToDto(game), HttpStatus.OK);
    }


    @PutMapping("/game/{gameId}/start")
    public ResponseEntity<Void> startGame(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
        gameAdminService.startGame(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/game/{gameId}/end")
    public ResponseEntity<Void> endGame(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
        gameAdminService.endGame(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/newgame/")
    public ResponseEntity<Integer> createGame(@RequestBody GameDto gameDto) throws ServiceException, MappingException, DaoException {
        Game game = new Game(gameDto.getGameName(), gameDto.getGameId(),  gameDto.getGameStarted());
        int gameId = gameAdminService.saveGame(game);
        return new ResponseEntity<>(gameId, HttpStatus.CREATED);
    }


    @DeleteMapping("/game/{gameId}/delete")
    public ResponseEntity<Integer> deleteGame(@PathVariable("gameId") int gameId) throws ServiceException, MappingException, DaoException {
        Game game = gameAdminService.getGame(gameId);
        gameAdminService.deleteGame(game);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> validateUser (@RequestParam String name) throws ServiceException, MappingException, DaoException{
        User user = gameAdminService.validateUser(name);
        if(user != null){
            UserDTO userDTO = dtoMapper.convertToDto(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        else
            return null;
    }

    @PostMapping("/game/{gameId}/user/{userName}")
    public ResponseEntity<Void> addUserToBackEnd(@PathVariable("gameId") int gameId, @PathVariable("userName") String userName) throws ServiceException, MappingException, DaoException{
        Game game = gameAdminService.getGame(gameId);
        for(User user: gameAdminService.getUsers()){
            if(user.playerName.equals(userName)){
                game.getGameUsers().add(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
