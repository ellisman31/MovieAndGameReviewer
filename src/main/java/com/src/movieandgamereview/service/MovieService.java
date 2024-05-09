package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.InformationDTO;
import com.src.movieandgamereview.dto.MovieDTO;
import com.src.movieandgamereview.dto.MovieGenreDTO;
import com.src.movieandgamereview.model.Information;
import com.src.movieandgamereview.model.Movie;
import com.src.movieandgamereview.model.MovieGenre;
import com.src.movieandgamereview.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieGenreService movieGenreService;
    @Autowired
    private InformationService informationService;

    public List<MovieDTO> getAllMovie() {
        return ((List<Movie>) movieRepository.findAll()).stream().map(this::convertToMovieDTO).collect(Collectors.toList());
    }

    public Movie findMovieById(Long movieId) {
        Optional<Movie> getMovie = movieRepository.findById(movieId);
        return getMovie.orElse(null);
    }

    public void saveMovie(Movie movie) {
        setMovieGenreForMovie(movie);
        movieRepository.save(movie);
    }

    public void updateMovie(Long movieId, Movie newMovieData) {
        Movie currentMovie = findMovieById(movieId);
        if (newMovieData.getMovieLength() > 0) {
            currentMovie.setMovieLength(newMovieData.getMovieLength());
        }
        if (newMovieData.getInformation() != null) {
            currentMovie.setInformation(newMovieData.getInformation());
        }
        if (newMovieData.getMovieGenre() != null) {
            currentMovie.setMovieGenre(newMovieData.getMovieGenre());
            if (currentMovie.getMovieGenre() != newMovieData.getMovieGenre()) {
                movieGenreService.removeMovieFromMovieGenre(currentMovie.getMovieGenre().getId(), currentMovie);
            }
        }
        saveMovie(currentMovie);
    }

    public MovieDTO findAndGetMovieDTOById(Long movieId) {
        Optional<Movie> getMovie = movieRepository.findById(movieId);
        return getMovie.map(this::convertToMovieDTO).orElse(null);
    }

    protected MovieDTO convertToMovieDTO(Movie movie) {
        InformationDTO informationDTO = informationService.findAndGetInformationDTOById(movie.getInformation().getId());
        MovieGenreDTO movieGenreDTO = movieGenreService.findAndGetMovieGenreDTOById(movie.getMovieGenre().getId());

        return new MovieDTO(movie.getMovieLength(), informationDTO, movieGenreDTO);
    }

    private void setMovieGenreForMovie(Movie movie) {
        MovieGenre getMovieGenre = movieGenreService.findMovieGenreById(movie.getMovieGenre().getId());
        if (getMovieGenre != null) {
            boolean isMovieGenreHasTheMovie = getMovieGenre.getMovies().contains(movie);
            if (!isMovieGenreHasTheMovie) {
                movieGenreService.addMovieToMovieGenre(movie.getMovieGenre().getId(), movie);
            }
        }
    }

}
