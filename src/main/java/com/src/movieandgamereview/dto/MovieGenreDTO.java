package com.src.movieandgamereview.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class MovieGenreDTO {
    private String name;
    private Set<MovieDTO> movies;

    public MovieGenreDTO(String name) {
        this.name = name;
        this.movies = new HashSet<>();
    }
}
