package com.movieandgamereview.informationservice.model;

import com.movieandgamereview.informationservice.group.Rates;
import com.movieandgamereview.informationservice.model.game.Game;
import com.movieandgamereview.informationservice.model.movie.Movie;
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
    private Rates name;
    private Set<Movie> movies;
    private Set<Game> games;

    public Rate(Rates name) {
        this.name = name;
        this.movies = new HashSet<>();
        this.games = new HashSet<>();
    }
}
