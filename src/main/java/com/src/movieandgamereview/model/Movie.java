package com.src.movieandgamereview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("movie")
public class Movie {
    @Id
    private Long id;
    private int movieLength;
    private AggregateReference<Information, Long> information;
    private AggregateReference<MovieGenre, Long> movieGenre;

    public Movie(int movieLength, AggregateReference<Information, Long> information, AggregateReference<MovieGenre, Long> movieGenre) {
        this.movieLength = movieLength;
        this.information = information;
        this.movieGenre = movieGenre;
    }
}
