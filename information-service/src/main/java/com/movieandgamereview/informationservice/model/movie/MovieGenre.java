package com.movieandgamereview.informationservice.model.movie;

import com.movieandgamereview.informationservice.group.MovieGenres;
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
    private MovieGenres name;
    private Set<Movie> movies;

    public MovieGenre(MovieGenres name) {
        this.name = name;
        this.movies = new HashSet<>();
    }
}
