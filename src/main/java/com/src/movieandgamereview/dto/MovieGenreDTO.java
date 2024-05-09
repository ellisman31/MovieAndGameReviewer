package com.src.movieandgamereview.dto;

import lombok.Data;

import java.util.Set;

@Data
public class MovieGenreDTO {
    private String name;
    private Set<MovieDTO> movies;

    public MovieGenreDTO(String name, Set<MovieDTO> movies) {
        this.name = name;
        this.movies = movies;
    }
}
