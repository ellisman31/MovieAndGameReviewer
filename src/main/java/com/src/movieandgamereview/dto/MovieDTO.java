package com.src.movieandgamereview.dto;

import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@Data
public class MovieDTO {
    private int movieLength;
    private InformationDTO information;
    private MovieGenreDTO movieGenre;

    public MovieDTO(int movieLength, InformationDTO information, MovieGenreDTO movieGenre) {
        this.movieLength = movieLength;
        this.information = information;
        this.movieGenre = movieGenre;
    }
}
