package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {
}
