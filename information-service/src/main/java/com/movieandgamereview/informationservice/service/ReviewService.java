package com.movieandgamereview.informationservice.service;

import com.movieandgamereview.informationservice.client.UserClient;
import com.movieandgamereview.informationservice.dto.ReviewDTO;
import com.movieandgamereview.informationservice.dto.game.GameDTO;
import com.movieandgamereview.informationservice.dto.movie.MovieDTO;
import com.movieandgamereview.informationservice.dto.user.UserDTO;
import com.movieandgamereview.informationservice.dto.user.UserReviewsDTO;
import com.movieandgamereview.informationservice.model.Review;
import com.movieandgamereview.informationservice.model.game.Game;
import com.movieandgamereview.informationservice.model.movie.Movie;
import com.movieandgamereview.informationservice.model.user.User;
import com.movieandgamereview.informationservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//TODO: CREATE METHODS TO GET USERS REVIEWS.
//TODO: CHECK ENUMS FOR GAMEGENRE, MOVIEGENRE, LANGUAGE AND RATE AT TEST FOR SAVE.
@Service
public class ReviewService {
    @Autowired(required=false)
    private ReviewRepository reviewRepository;
    @Autowired(required=false)
    private UserClient userClient;
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

    public UserReviewsDTO findAndGetUserReviewsByIdDTO(Long userId) {
        User getUser = userClient.getUserById(userId);
        return convertToUsersReviewsDTO(getUser);
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
        if (newReviewData.getDescription() != null) {
            currentReview.setDescription(newReviewData.getDescription());
        }
        saveReview(currentReview.get_user().getId(), currentReview.getMovie().getId(), currentReview.getGame().getId(), currentReview);
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
        UserDTO reviewUser = userClient.getUserDTOById(review.get_user().getId());
        MovieDTO movieReview = movieService.findAndGetMovieDTOById(review.getMovie().getId());
        GameDTO gameReview = gameService.findAndGetGameDTOById(review.getGame().getId());

        return new ReviewDTO(review.getDescription(), reviewUser, movieReview, gameReview);
    }

    public UserReviewsDTO convertToUsersReviewsDTO(User user) {
        Set<ReviewDTO> reviews = user.getReviews()
                .stream()
                .map(this::convertToReviewDTO)
                .collect(Collectors.toSet());

        return new UserReviewsDTO(reviews);
    }

    /*
    public void addReview(Long currentUserId, Review newReview) throws Exception {
        User currentUser = findUserById(currentUserId);
        currentUser.getReviews().add(newReview);
        updateUser(currentUserId, currentUser);
    }
    public Review updateReview(Long currentUserId, Review currentUserReview, Review newReviewData) throws Exception {
        User currentUser = findUserById(currentUserId);
        currentUser.getReviews().remove(currentUserReview);
        if (newReviewData.getGame() != null) {
            currentUserReview.setGame(newReviewData.getGame());
        }
        if (newReviewData.getMovie() != null) {
            currentUserReview.setMovie(newReviewData.getMovie());
        }
        if (newReviewData.getDescription() != null) {
            currentUserReview.setDescription(newReviewData.getDescription());
        }

        addReview(currentUserId, currentUserReview);
        return currentUserReview;
    }

    public void removeReview(Long currentUserId, Long currentUserReviewId) throws Exception {
        User currentUser = findUserById(currentUserId);
        Review getReview = reviewService.findReviewById(currentUserReviewId);
        if (currentUser.getReviews().contains(getReview)) {
            currentUser.getReviews().remove(getReview);
            updateUser(currentUserId, currentUser);
        }
    }
     */
}
