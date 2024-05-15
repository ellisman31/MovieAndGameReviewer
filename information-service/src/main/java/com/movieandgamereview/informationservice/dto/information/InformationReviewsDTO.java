package com.movieandgamereview.informationservice.dto.information;

import com.movieandgamereview.informationservice.dto.ReviewDTO;
import lombok.Data;

import java.util.Set;

@Data
public class InformationReviewsDTO {
    private Set<ReviewDTO> reviews;

    public InformationReviewsDTO(Set<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}
