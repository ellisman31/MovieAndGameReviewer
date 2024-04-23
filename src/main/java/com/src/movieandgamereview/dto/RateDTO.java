package com.src.movieandgamereview.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RateDTO {
    private String name;
    private Set<MovieDTO> movies;
    private Set<GameDTO> games;

    public RateDTO(String name) {
        this.name = name;
        this.movies = new HashSet<>();
        this.games = new HashSet<>();
    }
}
