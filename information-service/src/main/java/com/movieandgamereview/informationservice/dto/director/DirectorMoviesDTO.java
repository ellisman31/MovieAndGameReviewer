package com.movieandgamereview.informationservice.dto.director;

import com.movieandgamereview.informationservice.dto.movie.MovieDTO;
import lombok.Data;

import java.util.Set;

@Data
public class DirectorMoviesDTO {
    private Set<MovieDTO> movies;

    public DirectorMoviesDTO(Set<MovieDTO> movies) {
        this.movies = movies;
    }
}
