package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.game.GameGenre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameGenreRepository extends CrudRepository<GameGenre, Long> {
}
