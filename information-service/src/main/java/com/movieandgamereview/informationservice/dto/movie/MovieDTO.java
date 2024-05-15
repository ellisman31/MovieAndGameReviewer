package com.movieandgamereview.informationservice.dto.movie;

import com.movieandgamereview.informationservice.dto.information.InformationDTO;
import lombok.Data;

@Data
public class MovieDTO {
    private int movieLength;
    private InformationDTO information;

    public MovieDTO(int movieLength, InformationDTO information) {
        this.movieLength = movieLength;
        this.information = information;
    }
}
