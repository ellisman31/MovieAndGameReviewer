package com.src.movieandgamereview.dto;

import lombok.Data;

import java.util.Set;

@Data
public class GameGenreDTO {
    private String name;
    private Set<GameDTO> games;

    public GameGenreDTO(String name, Set<GameDTO> games) {
        this.name = name;
        this.games = games;
    }
}
