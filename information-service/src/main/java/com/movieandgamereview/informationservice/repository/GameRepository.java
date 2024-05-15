package com.movieandgamereview.informationservice.repository;

import com.movieandgamereview.informationservice.model.Information;
import com.movieandgamereview.informationservice.model.game.Game;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findByInformation(AggregateReference<Information, Long> information);
}
