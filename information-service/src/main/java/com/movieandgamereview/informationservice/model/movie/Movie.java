package com.movieandgamereview.informationservice.model.movie;

import com.movieandgamereview.informationservice.model.Information;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("movie")
public class Movie {
    @Id
    private Long id;
    private int movieLength;
    private AggregateReference<Information, Long> information;
    private Set<MovieGenre> movieGenres;

    public Movie(int movieLength, AggregateReference<Information, Long> information) {
        this.movieLength = movieLength;
        this.information = information;
        this.movieGenres = new HashSet<>();
    }
}
