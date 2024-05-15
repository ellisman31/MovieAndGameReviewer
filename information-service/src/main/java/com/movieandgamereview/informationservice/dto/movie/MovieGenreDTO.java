package com.movieandgamereview.informationservice.dto.movie;

import com.movieandgamereview.informationservice.group.MovieGenres;
import lombok.Data;

@Data
public class MovieGenreDTO {
    private MovieGenres name;

    public MovieGenreDTO(MovieGenres name) {
        this.name = name;
    }
}
