package com.movieandgamereview.informationservice.repository;

import com.movieandgamereview.informationservice.model.Information;
import com.movieandgamereview.informationservice.model.movie.Movie;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    Optional<Movie> findByInformation(AggregateReference<Information, Long> information);
}
