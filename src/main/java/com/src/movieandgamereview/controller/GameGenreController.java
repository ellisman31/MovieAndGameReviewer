package com.src.movieandgamereview.controller;

import com.src.movieandgamereview.dto.game.GameGenreDTO;
import com.src.movieandgamereview.dto.game.GameGenreGamesDTO;
import com.src.movieandgamereview.model.game.GameGenre;
import com.src.movieandgamereview.service.GameGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameGenreController {
    @Autowired
    private GameGenreService gameGenreService;

    @GetMapping("/getAllGameGenre")
    @ResponseBody
    public List<GameGenreDTO> getAllGame() {
        return gameGenreService.getAllGameGenre();
    }

    @GetMapping("/getGameById/{gameGenreId}")
    @ResponseBody
    public GameGenreDTO getGameById(@PathVariable("gameGenreId") Long gameGenreId) {
        return gameGenreService.findAndGetGameGenreDTOById(gameGenreId);
    }

    @GetMapping("/getGameGenresMoviesById/{gameGenreId}")
    @ResponseBody
    public GameGenreGamesDTO getGameGenresMoviesById(@PathVariable("gameGenreId") Long gameGenreId) {
        return gameGenreService.findAndGetGameGenreGamesDTOById(gameGenreId);
    }

    @PostMapping("/addGameGenre")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGameGenre(@RequestBody GameGenre gameGenre) {
        gameGenreService.saveGameGenre(gameGenre);
    }

    @PutMapping("/updateGameGenre/{gameGenreId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateGameGenre(@PathVariable("movieGenreId") Long gameGenreId, @RequestBody GameGenre gameGenre) {
        gameGenreService.updateGameGenre(gameGenreId, gameGenre);
    }

    @DeleteMapping("/deleteGameGenre/{gameGenreId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGameGenre(@PathVariable("gameGenreId") Long gameGenreId) {
        gameGenreService.deleteGameGenre(gameGenreId);
    }

    @PutMapping("/addGameToGameGenre/{gameId}/{gameGenreId}")
    @ResponseStatus(HttpStatus.OK)
    public void addGameToGameGenre(@PathVariable("gameId") Long gameId, @PathVariable("gameGenreId") Long gameGenreId) {
        gameGenreService.addGameToGameGenre(gameGenreId, gameId);
    }

    @PutMapping("/removeGameFromGameGenre/{gameId}/{gameGenreId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeGameFromGameGenre(@PathVariable("gameId") Long gameId, @PathVariable("gameGenreId") Long gameGenreId) {
        gameGenreService.removeGameFromGameGenre(gameGenreId, gameId);
    }
}
