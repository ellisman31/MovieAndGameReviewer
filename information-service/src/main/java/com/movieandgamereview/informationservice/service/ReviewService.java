package com.movieandgamereview.informationservice.service;

import com.movieandgamereview.informationservice.dto.ReviewDTO;
import com.movieandgamereview.informationservice.dto.game.GameDTO;
import com.movieandgamereview.informationservice.dto.movie.MovieDTO;
//import com.movieandgamereview.informationservice.dto.user.UserDTO;
import com.movieandgamereview.informationservice.model.Review;
import com.movieandgamereview.informationservice.model.game.Game;
import com.movieandgamereview.informationservice.model.movie.Movie;
import com.movieandgamereview.informationservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO: CONNECT WITH USER-SERVICE.
@Service
public class ReviewService {
    @Autowired(required=false)
    private ReviewRepository reviewRepository;
    //@Autowired(required=false)
    //private UserService userService;
    @Autowired(required=false)
    private MovieService movieService;
    @Autowired(required=false)
    private GameService gameService;
    @Autowired(required=false)
    private InformationService informationService;

    public List<ReviewDTO> getAllReview() {
        return ((List<Review>) reviewRepository.findAll()).stream().map(this::convertToReviewDTO).collect(Collectors.toList());
    }

    public Review findReviewById(Long currentReviewId) {
        Optional<Review> getReview = reviewRepository.findById(currentReviewId);
        return getReview.orElse(null);
    }

    public ReviewDTO findAndGetReviewDTOById(Long currentReviewId) {
        Optional<Review> getReview = reviewRepository.findById(currentReviewId);
        return getReview.map(this::convertToReviewDTO).orElse(null);
    }

    public List<Review> findReviewByMovie(AggregateReference<Movie, Long> movie) {
        return reviewRepository.findByMovie(movie);
    }

    public List<Review> findReviewByGame(AggregateReference<Game, Long> game) {
        return reviewRepository.findByGame(game);
    }

    public void saveReview(Long currentUserId, Long currentMovieId, Long currentGameId, Review newReview) throws Exception {
        if (currentMovieId > 0 && movieService.findMovieById(currentMovieId) != null) {
            newReview.setMovie(AggregateReference.to(currentMovieId));
        }
        if (currentGameId > 0 && gameService.findGameById(currentGameId) != null) {
            newReview.setGame(AggregateReference.to(currentGameId));
        }
        reviewRepository.save(newReview);
        //userService.addReview(currentUserId, newReview);
    }

    public void updateReview(Long currentReviewId, Review newReviewData) throws Exception {
        Review currentReview = findReviewById(currentReviewId);
        //Review updatedReview = userService.updateReview(currentReview.get_user().getId(),
                //currentReview, newReviewData);
        //saveReview(currentReviewId, updatedReview.getMovie().getId(), updatedReview.getGame().getId(), updatedReview);
    }

    public void deleteReview(Long currentReviewId) throws Exception {
        Review getReview = findReviewById(currentReviewId);
        informationService.removeReviewFromInformation(informationService.findInformationByReview(getReview).getId(), getReview.getId());
        //userService.removeReview(getReview.get_user().getId(), getReview.getId());
        reviewRepository.delete(getReview);
    }

    protected ReviewDTO convertToReviewDTO(Review review) {
        //UserDTO reviewUser = userService.findAndGetUserByIdDTO(review.get_user().getId());
        MovieDTO movieReview = movieService.findAndGetMovieDTOById(review.getMovie().getId());
        GameDTO gameReview = gameService.findAndGetGameDTOById(review.getGame().getId());

        return null;//new ReviewDTO(review.getDescription(), reviewUser, movieReview, gameReview);
    }

}
