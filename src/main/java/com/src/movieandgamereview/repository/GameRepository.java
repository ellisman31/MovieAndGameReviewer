package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.game.Game;
import com.src.movieandgamereview.model.Information;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findByInformation(AggregateReference<Information, Long> information);
}
