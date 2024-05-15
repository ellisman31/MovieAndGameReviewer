package com.movieandgamereview.informationservice.repository;

import com.movieandgamereview.informationservice.model.movie.MovieGenre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends CrudRepository<MovieGenre, Long> {
}
