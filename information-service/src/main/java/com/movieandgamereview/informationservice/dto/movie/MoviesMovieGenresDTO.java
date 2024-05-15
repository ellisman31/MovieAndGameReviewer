package com.movieandgamereview.informationservice.dto.movie;

import lombok.Data;

import java.util.Set;

@Data
public class MoviesMovieGenresDTO {
    private Set<MovieGenreDTO> movieGenre;

    public MoviesMovieGenresDTO(Set<MovieGenreDTO> movieGenre) {
        this.movieGenre = movieGenre;
    }
}
