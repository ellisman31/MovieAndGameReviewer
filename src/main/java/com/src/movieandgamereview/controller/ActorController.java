package com.src.movieandgamereview.controller;

import com.src.movieandgamereview.dto.actor.ActorDTO;
import com.src.movieandgamereview.dto.actor.ActorGamesDTO;
import com.src.movieandgamereview.dto.actor.ActorMoviesDTO;
import com.src.movieandgamereview.model.Actor;
import com.src.movieandgamereview.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActorController {
    @Autowired
    private ActorService actorService;

    @GetMapping("/getAllActor")
    @ResponseBody
    public List<ActorDTO> getAllActor() {
        return actorService.getAllActor();
    }

    @GetMapping("/getActorById/{actorId}")
    @ResponseBody
    public ActorDTO getActorById(@PathVariable("actorId") Long actorId) {
        return actorService.findAndGetActorDTOById(actorId);
    }

    @GetMapping("/getActorMoviesById/{actorId}")
    @ResponseBody
    public ActorMoviesDTO getActorMovies(@PathVariable("actorId") Long actorId) {
        return actorService.findAndGetActorMoviesDTOById(actorId);
    }

    @GetMapping("/getActorGamesById/{actorId}")
    @ResponseBody
    public ActorGamesDTO getActorGames(@PathVariable("actorId") Long actorId) {
        return actorService.findAndGetActorGamesDTOById(actorId);
    }

    @PostMapping("/addActor/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addActor(@PathVariable("personId") Long personId, @RequestBody Actor actor) {
        actorService.saveNewActor(personId, actor);
    }

    @PutMapping("/updateActor/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateActor(@PathVariable("actorId") Long actorId, @RequestBody Actor actor) {
        actorService.updateActor(actorId, actor);
    }

    @DeleteMapping("/deleteActor/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActor(@PathVariable("personId") Long actorId) {
        actorService.deleteActor(actorId);
    }

    @PutMapping("/addGameToGameGenre/{gameId}/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public void addActorToGame(@PathVariable("gameId") Long gameId, @PathVariable("actorId") Long actorId) {
        actorService.addActorToGame(actorId, gameId);
    }

    @PutMapping("/removeGameFromGameGenre/{gameId}/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeActorFromGame(@PathVariable("gameId") Long gameId, @PathVariable("actorId") Long actorId) {
        actorService.removeActorFromGame(actorId, gameId);
    }

    @PutMapping("/addGameToGameGenre/{movieId}/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public void addActorToMovie(@PathVariable("movieId") Long movieId, @PathVariable("actorId") Long actorId) {
        actorService.addActorToMovie(actorId, movieId);
    }

    @PutMapping("/removeGameFromGameGenre/{movieId}/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeActorFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("actorId") Long actorId) {
        actorService.removeActorFromMovie(actorId, movieId);
    }
}
