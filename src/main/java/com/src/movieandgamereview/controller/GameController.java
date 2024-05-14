package com.src.movieandgamereview.controller;

import com.src.movieandgamereview.dto.game.GameDTO;
import com.src.movieandgamereview.dto.game.GamesGameGenresDTO;
import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/getAllGame")
    @ResponseBody
    public List<GameDTO> getAllGame() {
        return gameService.getAllGame();
    }

    @GetMapping("/getGameById/{gameId}")
    @ResponseBody
    public GameDTO getGameById(@PathVariable("gameId") Long gameId) {
        return gameService.findAndGetGameDTOById(gameId);
    }

    @GetMapping("/getGamesGameGenresById/{gameId}")
    @ResponseBody
    public GamesGameGenresDTO getGamesGameGenresById(@PathVariable("gameId") Long gameId) {
        return gameService.findAndGetGamesGameGenresDTO(gameId);
    }

    @PostMapping("/addGame")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGame(@RequestBody Game game) {
        gameService.saveGame(game);
    }

    @PutMapping("/updateGame/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGame(@PathVariable("gameId") Long gameId, @RequestBody Game game) {
        gameService.updateGame(gameId, game);
    }

    @DeleteMapping("/deleteGame/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGame(@PathVariable("gameId") Long gameId) {
        gameService.deleteGame(gameId);
    }

    @PutMapping("/addGameGenreToGame/{gameGenreId}/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public void addGameGenreToGame(@PathVariable("gameGenreId") Long gameGenreId, @PathVariable("gameId") Long gameId) {
        gameService.addGameGenreToGame(gameId, gameGenreId);
    }

    @PutMapping("/removeGameGenreFromGame/{gameGenreId}/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeGameGenreFromGame(@PathVariable("gameGenreId") Long gameGenreId, @PathVariable("gameId") Long gameId) {
        gameService.removeGameGenreFromGame(gameId, gameGenreId);
    }
}
