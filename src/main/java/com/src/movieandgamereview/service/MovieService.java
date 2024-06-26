package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.information.InformationDTO;
import com.src.movieandgamereview.dto.movie.MovieDTO;
import com.src.movieandgamereview.dto.movie.MovieGenreDTO;
import com.src.movieandgamereview.dto.movie.MoviesMovieGenresDTO;
import com.src.movieandgamereview.model.Information;
import com.src.movieandgamereview.model.movie.Movie;
import com.src.movieandgamereview.model.movie.MovieGenre;
import com.src.movieandgamereview.model.Review;
import com.src.movieandgamereview.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieGenreService movieGenreService;
    @Autowired
    private InformationService informationService;
    @Autowired
    private ReviewService reviewService;

    public List<MovieDTO> getAllMovie() {
        return ((List<Movie>) movieRepository.findAll()).stream().map(this::convertToMovieDTO).collect(Collectors.toList());
    }

    public Movie findMovieById(Long currentMovieId) {
        Optional<Movie> getMovie = movieRepository.findById(currentMovieId);
        return getMovie.orElse(null);
    }

    public MovieDTO findAndGetMovieDTOById(Long currentMovieId) {
        Optional<Movie> getMovie = movieRepository.findById(currentMovieId);
        return getMovie.map(this::convertToMovieDTO).orElse(null);
    }

    public MoviesMovieGenresDTO findAndGetMoviesMovieGenresDTOById(Long currentMovieId) {
        Optional<Movie> getMovie = movieRepository.findById(currentMovieId);
        return getMovie.map(this::convertToMoviesMovieGenresDTO).orElse(null);
    }

    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void updateMovie(Long currentMovieId, Movie newMovieData) {
        Movie currentMovie = findMovieById(currentMovieId);
        if (newMovieData.getMovieLength() > 0) {
            currentMovie.setMovieLength(newMovieData.getMovieLength());
        }
        if (newMovieData.getInformation() != null) {
            currentMovie.setInformation(newMovieData.getInformation());
        }
        if (newMovieData.getMovieGenres() != null) {
            currentMovie.setMovieGenres(newMovieData.getMovieGenres());
        }
        saveMovie(currentMovie);
    }

    public void deleteMovie(Long currentMovieId) {
        Movie getMovie = findMovieById(currentMovieId);
        getMovie.getMovieGenres().forEach(movieGenre -> {
            MovieGenre getMoviegenre = movieGenreService.findMovieGenreById(movieGenre.getId());
            getMoviegenre.getMovies().remove(getMovie);
            movieGenreService.updateMovieGenre(movieGenre.getId(), getMoviegenre);

        });
        List<Review> reviews = reviewService.findReviewByMovie(AggregateReference.to(currentMovieId));
        reviews.forEach(review -> {
            try {
                reviewService.deleteReview(review.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        movieRepository.delete(getMovie);
    }

    public void addMovieGenreToMovie(Long currentMovieId, Long currentMovieGenreId) {
        Movie getMovie = findMovieById(currentMovieId);
        MovieGenre getMovieGenre = movieGenreService.findMovieGenreById(currentMovieGenreId);
        if (getMovieGenre != null && !getMovie.getMovieGenres().contains(getMovieGenre)) {
            getMovie.getMovieGenres().add(getMovieGenre);
            updateMovie(currentMovieId, getMovie);
        }
    }

    public void removeMovieGenreFromMovie(Long currentMovieId, Long currentMovieGenreId) {
        Movie getMovie = findMovieById(currentMovieId);
        MovieGenre getMovieGenre = movieGenreService.findMovieGenreById(currentMovieGenreId);
        if (getMovieGenre != null && getMovie.getMovieGenres().contains(getMovieGenre)) {
            getMovie.getMovieGenres().remove(getMovieGenre);
            updateMovie(currentMovieId, getMovie);
        }
    }

    protected MovieDTO convertToMovieDTO(Movie movie) {
        InformationDTO informationDTO = informationService.findAndGetInformationDTOById(movie.getInformation().getId());

        return new MovieDTO(movie.getMovieLength(), informationDTO);
    }

    protected MoviesMovieGenresDTO convertToMoviesMovieGenresDTO(Movie movie) {
        Set<MovieGenreDTO> movies = movie.getMovieGenres()
                .stream()
                .map(movieGenre -> movieGenreService.convertToMovieGenreDTO(movieGenre))
                .collect(Collectors.toSet());
        return new MoviesMovieGenresDTO(movies);
    }

    public Movie findMovieByInformation(AggregateReference<Information, Long> information) {
        return movieRepository.findByInformation(information).orElse(null);
    }
}
