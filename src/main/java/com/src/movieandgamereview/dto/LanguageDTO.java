package com.src.movieandgamereview.dto;

import lombok.Data;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@Data
public class LanguageDTO {
    private String name;
    private GameDTO game;
    private MovieDTO movie;

    public LanguageDTO(String name, GameDTO game, MovieDTO movie) {
        this.name = name;
        this.game = game;
        this.movie = movie;
    }
}
