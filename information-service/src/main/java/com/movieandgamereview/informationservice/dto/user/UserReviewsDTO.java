package com.movieandgamereview.informationservice.dto.user;

import com.movieandgamereview.informationservice.dto.ReviewDTO;
import lombok.Data;

import java.util.Set;

@Data
public class UserReviewsDTO {
    private Set<ReviewDTO> reviews;

    public UserReviewsDTO(Set<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
