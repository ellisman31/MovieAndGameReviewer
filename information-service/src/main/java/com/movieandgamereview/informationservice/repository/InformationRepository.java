package com.movieandgamereview.informationservice.repository;

import com.movieandgamereview.informationservice.model.Director;
import com.movieandgamereview.informationservice.model.Information;
import com.movieandgamereview.informationservice.model.Language;
import com.movieandgamereview.informationservice.model.Rate;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationRepository extends CrudRepository<Information, Long> {

    List<Information> findByRate(AggregateReference<Rate, Long> rate);
    List<Information> findBy_language(AggregateReference<Language, Long> language);
    List<Information> findByDirector(AggregateReference<Director, Long> director);

}
