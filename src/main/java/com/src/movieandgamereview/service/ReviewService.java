package com.src.movieandgamereview.service;

import com.src.movieandgamereview.dto.GameDTO;
import com.src.movieandgamereview.dto.MovieDTO;
import com.src.movieandgamereview.dto.ReviewDTO;
import com.src.movieandgamereview.dto.UserDTO;
import com.src.movieandgamereview.model.Review;
import com.src.movieandgamereview.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ReviewDTO> getAllReview() {
        return ((List<Review>) reviewRepository.findAll()).stream().map(this::convertToReviewDTO).collect(Collectors.toList());
    }

    public Review findReviewById(Long reviewId) {
        Optional<Review> getReview = reviewRepository.findById(reviewId);
        return getReview.orElse(null);
    }

    public ReviewDTO findAndGetReviewDTOById(Long reviewId) {
        Optional<Review> getReview = reviewRepository.findById(reviewId);
        return getReview.map(this::convertToReviewDTO).orElse(null);
    }

    public void saveReview(Review newReview) {
        reviewRepository.save(newReview);
        userService.addReview(newReview.get_user().getId(), newReview);
    }

    public void updateReview(Long reviewId, Review newReviewData) {
        Review currentReview = findReviewById(reviewId);
        Review updatedReview = userService.updateReview(currentReview.get_user().getId(),
                currentReview, newReviewData);
        reviewRepository.save(updatedReview);
    }

    public void deleteReview(Long reviewId) {
        Review currentReview = findReviewById(reviewId);
        reviewRepository.delete(currentReview);
        userService.removeReview(currentReview.get_user().getId(), currentReview);
    }

    protected ReviewDTO convertToReviewDTO(Review review) {
        UserDTO reviewUser = userService.findAndGetUserByIdDTO(review.get_user().getId());
        MovieDTO movieReview = movieService.findAndGetMovieDTOById(review.getMovie().getId());
        GameDTO gameReview = gameService.findAndGetGameDTOById(review.getGame().getId());

        return new ReviewDTO(review.getDescription(),
                reviewUser, movieReview, gameReview);
    }

}
