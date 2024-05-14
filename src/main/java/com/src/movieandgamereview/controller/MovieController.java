package com.src.movieandgamereview.controller;

import com.src.movieandgamereview.dto.movie.MovieDTO;
import com.src.movieandgamereview.dto.movie.MoviesMovieGenresDTO;
import com.src.movieandgamereview.model.movie.Movie;
import com.src.movieandgamereview.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/getAllMovie")
    @ResponseBody
    public List<MovieDTO> getAllMovie() {
        return movieService.getAllMovie();
    }

    @GetMapping("/getMovieById/{movieId}")
    @ResponseBody
    public MovieDTO getMovieById(@PathVariable("movieId") Long movieId) {
        return movieService.findAndGetMovieDTOById(movieId);
    }

    @GetMapping("/getMoviesMovieGenresById/{movieId}")
    @ResponseBody
    public MoviesMovieGenresDTO getMoviesMovieGenresById(@PathVariable("movieId") Long movieId) {
        return movieService.findAndGetMoviesMovieGenresDTOById(movieId);
    }

    @PostMapping("/addMovie")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@RequestBody Movie movie) {
        movieService.saveMovie(movie);
    }

    @PutMapping("/updateMovie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovie(@PathVariable("movieId") Long movieId, @RequestBody Movie movie) {
        movieService.updateMovie(movieId, movie);
    }

    @DeleteMapping("/deleteMovie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.deleteMovie(movieId);
    }

    @PutMapping("/addMovieGenreToMovie/{movieGenreId}/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void addMovieGenreToMovie(@PathVariable("movieGenreId") Long movieGenreId, @PathVariable("movieId") Long movieId) {
        movieService.addMovieGenreToMovie(movieId, movieGenreId);
    }

    @PutMapping("/removeMovieGenreFromMovie/{movieGenreId}/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeMovieGenreFromMovieGenre(@PathVariable("movieGenreId") Long movieGenreId, @PathVariable("movieId") Long movieId) {
        movieService.removeMovieGenreFromMovie(movieId, movieGenreId);
    }
}
