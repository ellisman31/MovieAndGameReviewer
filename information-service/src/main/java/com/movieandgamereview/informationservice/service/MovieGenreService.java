package com.movieandgamereview.informationservice.service;

import com.movieandgamereview.informationservice.dto.movie.MovieDTO;
import com.movieandgamereview.informationservice.dto.movie.MovieGenreDTO;
import com.movieandgamereview.informationservice.dto.movie.MovieGenreMoviesDTO;
import com.movieandgamereview.informationservice.model.movie.Movie;
import com.movieandgamereview.informationservice.model.movie.MovieGenre;
import com.movieandgamereview.informationservice.repository.MovieGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieGenreService {
    @Autowired(required=false)
    private MovieGenreRepository movieGenreRepository;
    @Autowired(required=false)
    private MovieService movieService;

    public List<MovieGenreDTO> getAllMovieGenre() {
        return ((List<MovieGenre>) movieGenreRepository.findAll()).stream().map(this::convertToMovieGenreDTO).collect(Collectors.toList());
    }

    public MovieGenre findMovieGenreById(Long currentMovieGenreId) {
        Optional<MovieGenre> getMovieGenre = movieGenreRepository.findById(currentMovieGenreId);
        return getMovieGenre.orElse(null);
    }

    public MovieGenreDTO findAndGetMovieGenreDTOById(Long currentMovieGenreId) {
        Optional<MovieGenre> getMovieGenre = movieGenreRepository.findById(currentMovieGenreId);
        return getMovieGenre.map(this::convertToMovieGenreDTO).orElse(null);
    }

    public MovieGenreMoviesDTO findAndGetMoviesDTOById(Long currentMovieGenreId) {
        Optional<MovieGenre> getMovieGenre = movieGenreRepository.findById(currentMovieGenreId);
        return getMovieGenre.map(this::convertMovieGenreMoviesToDTO).orElse(null);
    }

    public void saveMovieGenre(MovieGenre movieGenre) {
        movieGenreRepository.save(movieGenre);
    }

    public void updateMovieGenre(Long currentMovieGenreId, MovieGenre newMovieGenreData) {
        MovieGenre currentMovieGenre = findMovieGenreById(currentMovieGenreId);
        if (newMovieGenreData.getName() != null) {
            currentMovieGenre.setName(newMovieGenreData.getName());
        }
        if (newMovieGenreData.getMovies() != null) {
            currentMovieGenre.setMovies(newMovieGenreData.getMovies());
        }
        saveMovieGenre(currentMovieGenre);
    }

    public void addMovieToMovieGenre(Long currentMovieGenreId, Long currentMovieId) {
        MovieGenre currentMovieGenre = findMovieGenreById(currentMovieGenreId);
        Movie getMovie = movieService.findMovieById(currentMovieId);
        if (getMovie != null && !currentMovieGenre.getMovies().contains(getMovie)) {
            currentMovieGenre.getMovies().add(getMovie);
            updateMovieGenre(currentMovieGenre.getId(), currentMovieGenre);
        }
    }

    public void removeMovieFromMovieGenre(Long currentMovieGenreId, Long currentMovieId) {
        MovieGenre currentMovieGenre = findMovieGenreById(currentMovieGenreId);
        Movie getMovie = movieService.findMovieById(currentMovieId);
        if (getMovie != null && currentMovieGenre.getMovies().contains(getMovie)) {
            currentMovieGenre.getMovies().remove(getMovie);
            updateMovieGenre(currentMovieGenre.getId(), currentMovieGenre);
        }
    }

    public void deleteMovieGenre(Long currentMovieGenreId) {
        MovieGenre currentMovieGenre = findMovieGenreById(currentMovieGenreId);
        currentMovieGenre.getMovies().forEach(movie -> {
          Movie getMovie = movieService.findMovieById(movie.getId());
            getMovie.getMovieGenres().remove(currentMovieGenre);
          movieService.updateMovie(movie.getId(), getMovie);
        });
        movieGenreRepository.delete(currentMovieGenre);
    }

    protected MovieGenreDTO convertToMovieGenreDTO(MovieGenre movieGenre) {
        return new MovieGenreDTO(movieGenre.getName());
    }

    protected MovieGenreMoviesDTO convertMovieGenreMoviesToDTO(MovieGenre movieGenre) {
        Set<MovieDTO> movies = movieGenre.getMovies()
                .stream()
                .map(movieService::convertToMovieDTO)
                .collect(Collectors.toSet());

        return new MovieGenreMoviesDTO(movies);
    }

}
