package com.src.movieandgamereview.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class GameGenreDTO {
    private String name;
    private Set<GameDTO> games;

    public GameGenreDTO(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }
}
