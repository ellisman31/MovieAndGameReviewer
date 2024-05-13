package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.information.InformationDTO;
import com.src.movieandgamereview.dto.movie.MovieDTO;
import com.src.movieandgamereview.dto.movie.MovieGenreDTO;
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

    public void saveMovie(Movie movie) {
        setMovieGenreForMovie(movie);
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
        if (newMovieData.getMovieGenre() != null) {
            currentMovie.setMovieGenre(newMovieData.getMovieGenre());
            if (currentMovie.getMovieGenre() != newMovieData.getMovieGenre()) {
                movieGenreService.removeMovieFromMovieGenre(currentMovie.getMovieGenre().getId(), currentMovie);
            }
        }
        saveMovie(currentMovie);
    }

    public void deleteMovie(Long currentMovieId) {
        Movie getMovie = findMovieById(currentMovieId);
        MovieGenre moviegenre = movieGenreService.findMovieGenreById(getMovie.getMovieGenre().getId());
        moviegenre.getMovies().remove(getMovie);
        movieGenreService.updateMovieGenre(moviegenre.getId(), moviegenre);
        List<Review> reviews = reviewService.findReviewByMovie(AggregateReference.to(currentMovieId));
        reviews.forEach(review -> {
            reviewService.deleteReview(review.getId());
        });
        movieRepository.delete(getMovie);
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

    public Movie findMovieByInformation(AggregateReference<Information, Long> information) {
        return movieRepository.findByInformation(information).orElse(null);
    }
}
