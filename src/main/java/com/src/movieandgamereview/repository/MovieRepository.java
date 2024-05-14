package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.Information;
import com.src.movieandgamereview.model.movie.Movie;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    Optional<Movie> findByInformation(AggregateReference<Information, Long> information);
}
