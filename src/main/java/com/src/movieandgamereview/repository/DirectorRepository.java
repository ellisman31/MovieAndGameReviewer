package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.Director;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Long> {
}
