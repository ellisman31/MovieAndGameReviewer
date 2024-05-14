package com.src.movieandgamereview.model;

import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.movie.Movie;
import com.src.movieandgamereview.model.user.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("review")
public class Review {
    @Id
    private Long id;
    private String description;
    private AggregateReference<User, Long> _user;
    private AggregateReference<Movie, Long> movie;
    private AggregateReference<Game, Long> game;

    public Review(String description, AggregateReference<User, Long> _user, AggregateReference<Movie, Long> movie, AggregateReference<Game, Long> game) {
        this.description = description;
        this._user = _user;
        this.movie = movie;
        this.game = game;
    }
}
