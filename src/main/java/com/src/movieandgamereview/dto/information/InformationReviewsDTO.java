package com.src.movieandgamereview.dto.information;

import com.src.movieandgamereview.dto.ReviewDTO;
import lombok.Data;

import java.util.Set;

@Data
public class InformationReviewsDTO {
    private Set<ReviewDTO> reviews;

    public InformationReviewsDTO(Set<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
