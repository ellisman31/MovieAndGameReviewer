package com.movieandgamereview.informationservice.controller;

import com.movieandgamereview.informationservice.dto.ReviewDTO;
import com.movieandgamereview.informationservice.model.Review;
import com.movieandgamereview.informationservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/getAllReview")
    @ResponseBody
    public List<ReviewDTO> getAllReview() {
        return reviewService.getAllReview();
    }

    @GetMapping("/findReviewById/{reviewId}")
    @ResponseBody
    public ReviewDTO getReviewById(@PathVariable("reviewId") Long reviewId) {
        return reviewService.findAndGetReviewDTOById(reviewId);
    }

    @PostMapping("/addReview/{userId}/{movieId}/{gameId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUserReview(@PathVariable("userId") Long userId, @PathVariable("movieId") Long movieId, @PathVariable("gameId") Long gameId, @RequestBody Review review) throws Exception {
        reviewService.saveReview(userId, movieId, gameId, review);
    }

    @PutMapping("/updateReview/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody Review review) throws Exception {
        reviewService.updateReview(reviewId, review);
    }

    @DeleteMapping("/removeReviewFromUser/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReviewFromUser(@PathVariable("reviewId") Long reviewId) throws Exception {
        reviewService.deleteReview(reviewId);
    }
}
