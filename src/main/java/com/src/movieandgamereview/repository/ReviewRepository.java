package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.movie.Movie;
import com.src.movieandgamereview.model.Review;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByMovie(AggregateReference<Movie, Long> movie);
    List<Review> findByGame(AggregateReference<Game, Long> game);

}
