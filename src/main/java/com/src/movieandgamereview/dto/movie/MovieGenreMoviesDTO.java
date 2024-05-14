package com.src.movieandgamereview.dto.movie;

import lombok.Data;

import java.util.Set;

@Data

public class MovieGenreMoviesDTO {
    private Set<MovieDTO> movies;

    public MovieGenreMoviesDTO(Set<MovieDTO> movies) {
        this.movies = movies;
    }
}
