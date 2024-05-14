package com.src.movieandgamereview.repository;

import com.src.movieandgamereview.model.Director;
import com.src.movieandgamereview.model.Information;
import com.src.movieandgamereview.model.Language;
import com.src.movieandgamereview.model.Rate;
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
