package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.MovieGenre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieGenreRepository extends CrudRepository<MovieGenre, Long> {
}
