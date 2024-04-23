package com.src.movieandgamereview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Table("information")
public class Information {
    @Id
    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private int budget;
    private AggregateReference<Rate, Long> rate;
    private AggregateReference<Language, Long> _language;
    private AggregateReference<Review, Long> review;
    private AggregateReference<Director, Long> director;
    private Set<Actor> actors;

    public Information(String title, String description, LocalDate releaseDate, int budget, AggregateReference<Rate, Long> rate, AggregateReference<Language, Long> _language, AggregateReference<Review, Long> review, AggregateReference<Director, Long> director) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.budget = budget;
        this.rate = rate;
        this._language = _language;
        this.review = review;
        this.director = director;
        this.actors = new HashSet<>();
    }
}
