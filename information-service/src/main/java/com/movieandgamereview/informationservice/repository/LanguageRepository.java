package com.movieandgamereview.informationservice.repository;

import com.movieandgamereview.informationservice.model.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long> {
}
