package com.src.movieandgamereview.dto.movie;

import com.src.movieandgamereview.group.MovieGenres;
import lombok.Data;

import java.util.Set;

@Data
public class MovieGenreDTO {
    private MovieGenres name;

    public MovieGenreDTO(MovieGenres name) {
        this.name = name;
    }
}
