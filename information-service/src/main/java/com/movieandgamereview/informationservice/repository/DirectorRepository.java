package com.movieandgamereview.informationservice.repository;

import com.movieandgamereview.informationservice.model.Director;
import com.movieandgamereview.informationservice.model.Person;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Long> {

    Optional<Director> findByPerson(AggregateReference<Person, Long> person);

}
