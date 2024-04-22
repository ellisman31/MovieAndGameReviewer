package com.src.movieandgamereview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("_language")
public class Language {
    @Id
    private Long id;
    private String name;
    private AggregateReference<Game, Long> game;
    private AggregateReference<Movie, Long> movie;

    public Language(String name, AggregateReference<Game, Long> game, AggregateReference<Movie, Long> movie) {
        this.name = name;
        this.game = game;
        this.movie = movie;
    }
}
