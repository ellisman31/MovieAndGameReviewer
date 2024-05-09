package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.MovieDTO;
import com.src.movieandgamereview.dto.MovieGenreDTO;
import com.src.movieandgamereview.model.Movie;
import com.src.movieandgamereview.model.MovieGenre;
import com.src.movieandgamereview.repository.MovieGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieGenreService {

    @Autowired
    private MovieGenreRepository movieGenreRepository;
    @Autowired
    private MovieService movieService;

    public List<MovieGenreDTO> getAllMovieGenre() {
        return ((List<MovieGenre>) movieGenreRepository.findAll()).stream().map(this::convertToMovieGenreDTO).collect(Collectors.toList());
    }

    public MovieGenre findMovieGenreById(Long movieGenreId) {
        Optional<MovieGenre> getMovieGenre = movieGenreRepository.findById(movieGenreId);
        return getMovieGenre.orElse(null);
    }

    public MovieGenreDTO findAndGetMovieGenreDTOById(Long movieGenreId) {
        Optional<MovieGenre> getMovieGenre = movieGenreRepository.findById(movieGenreId);
        return getMovieGenre.map(this::convertToMovieGenreDTO).orElse(null);
    }

    public void saveMovieGenre(MovieGenre movieGenre) {
        movieGenreRepository.save(movieGenre);
    }

    public void updateMovieGenre(Long currentMovieGenreId, MovieGenre newMovieGenreData) {
        MovieGenre currentMovieGenre = findMovieGenreById(currentMovieGenreId);
        if (newMovieGenreData.getName() != null) {
            currentMovieGenre.setName(newMovieGenreData.getName());
        }
        saveMovieGenre(currentMovieGenre);
    }

    public void addMovieToMovieGenre(Long currentMovieGenreId, Movie movie) {
        MovieGenre currentMovieGenre = findMovieGenreById(currentMovieGenreId);
        currentMovieGenre.getMovies().add(movie);
        saveMovieGenre(currentMovieGenre);
    }

    public void removeMovieFromMovieGenre(Long currentMovieGenreId, Movie movie) {
        MovieGenre currentMovieGenre = findMovieGenreById(currentMovieGenreId);
        currentMovieGenre.getMovies().remove(movie);
        saveMovieGenre(currentMovieGenre);
    }
    protected MovieGenreDTO convertToMovieGenreDTO(MovieGenre movieGenre) {
        Set<MovieDTO> movies = movieGenre.getMovies()
                .stream()
                .map(movieService::convertToMovieDTO)
                .collect(Collectors.toSet());

        return new MovieGenreDTO(movieGenre.getName(), movies);
    }

}
