package com.movieandgamereview.informationservice.controller;

import com.movieandgamereview.informationservice.dto.director.DirectorDTO;
import com.movieandgamereview.informationservice.dto.director.DirectorGamesDTO;
import com.movieandgamereview.informationservice.dto.director.DirectorMoviesDTO;
import com.movieandgamereview.informationservice.model.Director;
import com.movieandgamereview.informationservice.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DirectorController {
    @Autowired
    private DirectorService directorService;

    @GetMapping("/getAllDirector")
    @ResponseBody
    public List<DirectorDTO> getAllDirector() {
        return directorService.getAllDirector();
    }

    @GetMapping("/getDirectorById/{directorId}")
    @ResponseBody
    public DirectorDTO getDirectorById(@PathVariable("directorId") Long directorId) {
        return directorService.findAndGetDirectorDTOById(directorId);
    }

    @GetMapping("/getDirectorMoviesById/{directorId}")
    @ResponseBody
    public DirectorMoviesDTO getDirectorMovies(@PathVariable("directorId") Long directorId) {
        return directorService.findAndGetDirectorMoviesDTOById(directorId);
    }

    @GetMapping("/getDirectorGamesById/{directorId}")
    @ResponseBody
    public DirectorGamesDTO getDirectorGames(@PathVariable("directorId") Long directorId) {
        return directorService.findAndGetDirectorGamesDTOById(directorId);
    }

    @PostMapping("/addDirector/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDirector(@PathVariable("personId") Long personId, @RequestBody Director director) {
        directorService.saveNewDirector(personId, director);
    }

    @PutMapping("/updateDirector/{directorId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDirector(@PathVariable("directorId") Long directorId, @RequestBody Director director) {
        directorService.updateDirector(directorId, director);
    }

    @DeleteMapping("/deleteDirector/{directorId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteActor(@PathVariable("directorId") Long directorId) {
        directorService.deleteDirector(directorId);
    }

    @PutMapping("/addDirectorToMovie/{movieId}/{directorId}")
    @ResponseStatus(HttpStatus.OK)
    public void addDirectorToMovie(@PathVariable("movieId") Long movieId, @PathVariable("directorId") Long directorId) {
        directorService.addDirectorToMovie(directorId, movieId);
    }

    @PutMapping("/removeDirectorFromMovie/{movieId}/{directorId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeDirectorFromMovie(@PathVariable("movieId") Long movieId, @PathVariable("directorId") Long directorId) {
        directorService.removeDirectorFromMovie(directorId, movieId);
    }

    @PutMapping("/addDirectorToGame/{gameId}/{directorId}")
    @ResponseStatus(HttpStatus.OK)
    public void addDirectorToGame(@PathVariable("gameId") Long gameId, @PathVariable("directorId") Long directorId) {
        directorService.addDirectorToGame(directorId, gameId);
    }

    @PutMapping("/removeDirectorFromGame/{gameId}/{directorId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeDirectorFromGame(@PathVariable("gameId") Long gameId, @PathVariable("directorId") Long directorId) {
        directorService.removeDirectorFromGame(directorId, gameId);
    }
}
