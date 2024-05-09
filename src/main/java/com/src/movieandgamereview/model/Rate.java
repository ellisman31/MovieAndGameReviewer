package com.src.movieandgamereview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("rate")
public class Rate {
    @Id
    private Long id;
    //TODO: CRATE ENUM FOR RATES.
    private String name;
    private Set<Movie> movies;
    private Set<Game> games;

    public Rate(String name) {
        this.name = name;
        this.movies = new HashSet<>();
        this.games = new HashSet<>();
    }
}
