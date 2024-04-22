package com.src.movieandgamereview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("game_genre")
public class GameGenre {
    @Id
    private Long id;
    private String name;
    private Set<Game> games;

    public GameGenre(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }
}
