package com.movieandgamereview.informationservice.model;

import com.movieandgamereview.informationservice.model.game.Game;
import com.movieandgamereview.informationservice.model.movie.Movie;
//import com.movieandgamereview.informationservice.model.user.User;
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
    //private AggregateReference<User, Long> _user;
    private AggregateReference<Movie, Long> movie;
    private AggregateReference<Game, Long> game;

    //AggregateReference<User, Long> _user
    public Review(String description, AggregateReference<Movie, Long> movie, AggregateReference<Game, Long> game) {
        this.description = description;
        //this._user = _user;
        this.movie = movie;
        this.game = game;
    }
}
