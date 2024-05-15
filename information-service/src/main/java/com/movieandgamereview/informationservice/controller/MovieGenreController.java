package com.movieandgamereview.informationservice.controller;

import com.movieandgamereview.informationservice.dto.movie.MovieGenreDTO;
import com.movieandgamereview.informationservice.dto.movie.MovieGenreMoviesDTO;
import com.movieandgamereview.informationservice.model.movie.MovieGenre;
import com.movieandgamereview.informationservice.service.MovieGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieGenreController {
    @Autowired
    private MovieGenreService movieGenreService;

    @GetMapping("/getAllMovieGenre")
    @ResponseBody
    public List<MovieGenreDTO> getAllMovie() {
        return movieGenreService.getAllMovieGenre();
    }

    @GetMapping("/getMovieById/{movieGenreId}")
    @ResponseBody
    public MovieGenreDTO getMovieById(@PathVariable("movieId") Long movieGenreId) {
        return movieGenreService.findAndGetMovieGenreDTOById(movieGenreId);
    }

    @GetMapping("/getMovieGenresMoviesById/{movieGenreId}")
    @ResponseBody
    public MovieGenreMoviesDTO getMovieGenresMoviesById(@PathVariable("movieGenreId") Long movieGenreId) {
        return movieGenreService.findAndGetMoviesDTOById(movieGenreId);
    }

    @PostMapping("/addMovieGenre")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovieGenre(@RequestBody MovieGenre movieGenre) {
        movieGenreService.saveMovieGenre(movieGenre);
    }

    @PutMapping("/updateMovieGenre/{movieGenreId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovieGenre(@PathVariable("movieGenreId") Long movieGenreId, @RequestBody MovieGenre movieGenre) {
        movieGenreService.updateMovieGenre(movieGenreId, movieGenre);
    }

    @DeleteMapping("/deleteMovieGenre/{movieGenreId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovieGenre(@PathVariable("movieGenreId") Long movieGenreId) {
        movieGenreService.deleteMovieGenre(movieGenreId);
    }

    @PutMapping("/addMovieToMovieGenre/{movieId}/{movieGenreId}")
    @ResponseStatus(HttpStatus.OK)
    public void addMovieToMovieGenre(@PathVariable("movieId") Long movieId, @PathVariable("movieGenreId") Long movieGenreId) {
        movieGenreService.addMovieToMovieGenre(movieGenreId, movieId);
    }

    @PutMapping("/removeMovieFromMovieGenre/{movieId}/{movieGenreId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeMovieFromMovieGenre(@PathVariable("movieId") Long movieId, @PathVariable("movieGenreId") Long movieGenreId) {
        movieGenreService.removeMovieFromMovieGenre(movieGenreId, movieId);
    }
}
