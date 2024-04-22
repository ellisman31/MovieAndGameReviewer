package com.src.movieandgamereview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@Data
public class Review {
    @Id
    private Long id;
    private String description;
    private AggregateReference<User, Long> user;
    private AggregateReference<Movie, Long> movie;
    private AggregateReference<Game, Long> game;

    public Review(String description, AggregateReference<User, Long> user, AggregateReference<Movie, Long> movie, AggregateReference<Game, Long> game) {
        this.description = description;
        this.user = user;
        this.movie = movie;
        this.game = game;
    }
}
