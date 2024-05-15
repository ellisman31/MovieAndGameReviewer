package com.movieandgamereview.informationservice.repository;

import com.movieandgamereview.informationservice.model.game.GameGenre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameGenreRepository extends CrudRepository<GameGenre, Long> {
}
