package com.movieandgamereview.informationservice.repository;

import com.movieandgamereview.informationservice.model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {
}
