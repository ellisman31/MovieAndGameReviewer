package com.src.movieandgamereview.dto.movie;

import com.src.movieandgamereview.dto.information.InformationDTO;
import lombok.Data;

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
