package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.game.GameDTO;
import com.src.movieandgamereview.dto.movie.MovieDTO;
import com.src.movieandgamereview.dto.ReviewDTO;
import com.src.movieandgamereview.dto.user.UserDTO;
import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.movie.Movie;
import com.src.movieandgamereview.model.Review;
import com.src.movieandgamereview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private GameService gameService;
    @Autowired
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

    public void saveReview(Long currentUserId, Review newReview) {
        reviewRepository.save(newReview);
        userService.addReview(currentUserId, newReview);
    }

    public void updateReview(Long currentReviewId, Review newReviewData) {
        Review currentReview = findReviewById(currentReviewId);
        Review updatedReview = userService.updateReview(currentReview.get_user().getId(),
                currentReview, newReviewData);
        reviewRepository.save(updatedReview);
    }

    public void deleteReview(Long currentReviewId) {
        Review getReview = findReviewById(currentReviewId);
        informationService.removeReviewFromInformation(informationService.findInformationByReview(getReview).getId(), getReview);
        userService.removeReview(getReview.get_user().getId(), getReview);
        reviewRepository.delete(getReview);
    }

    public List<Review> findReviewByMovie(AggregateReference<Movie, Long> movie) {
        return reviewRepository.findByMovie(movie);
    }

    public List<Review> findReviewByGame(AggregateReference<Game, Long> game) {
        return reviewRepository.findByGame(game);
    }

    protected ReviewDTO convertToReviewDTO(Review review) {
        UserDTO reviewUser = userService.findAndGetUserByIdDTO(review.get_user().getId());
        MovieDTO movieReview = movieService.findAndGetMovieDTOById(review.getMovie().getId());
        GameDTO gameReview = gameService.findAndGetGameDTOById(review.getGame().getId());

        return new ReviewDTO(review.getDescription(),
                reviewUser, movieReview, gameReview);
    }

}
