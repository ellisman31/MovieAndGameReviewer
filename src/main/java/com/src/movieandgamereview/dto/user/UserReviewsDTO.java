package com.src.movieandgamereview.dto.user;

import com.src.movieandgamereview.dto.ReviewDTO;
import lombok.Data;

import java.util.Set;

@Data
public class UserReviewsDTO {
    private Set<ReviewDTO> reviews;

    public UserReviewsDTO(Set<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
