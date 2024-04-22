package com.src.movieandgamereview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("actor")
public class Actor {
    @Id
    private Long id;
    private AggregateReference<Person, Long> person;
    private Set<Movie> movies;
    private Set<Game> games;

    public Actor(AggregateReference<Person, Long> person) {
        this.person = person;
        this.movies = new HashSet<>();
        this.games = new HashSet<>();
    }
}
