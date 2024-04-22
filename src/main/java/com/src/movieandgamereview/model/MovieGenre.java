package com.src.movieandgamereview.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@Table("movie_genre")
public class MovieGenre {
    @Id
    private Long id;
    private String name;
    private Set<Movie> movies;

    public MovieGenre(String name) {
        this.name = name;
        this.movies = new HashSet<>();
    }
}
