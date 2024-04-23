package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.Information;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends CrudRepository<Information, Long> {
}
