package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.Actor;
import com.src.movieandgamereview.model.Person;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {
    Optional<Actor> findByPerson(AggregateReference<Person, Long> person);
}
