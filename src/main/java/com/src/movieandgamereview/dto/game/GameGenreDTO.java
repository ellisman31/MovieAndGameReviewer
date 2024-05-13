package com.src.movieandgamereview.dto.game;

import lombok.Data;

import java.util.Set;

@Data
public class GameGenreDTO {
    private String name;

    public GameGenreDTO(String name) {
        this.name = name;
    }
}
