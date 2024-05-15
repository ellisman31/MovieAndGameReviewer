package com.movieandgamereview.informationservice.repository;

import com.movieandgamereview.informationservice.model.Review;
import com.movieandgamereview.informationservice.model.game.Game;
import com.movieandgamereview.informationservice.model.movie.Movie;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByMovie(AggregateReference<Movie, Long> movie);
    List<Review> findByGame(AggregateReference<Game, Long> game);

}
