package com.movieandgamereview.informationservice.dto.rate;

import com.movieandgamereview.informationservice.dto.movie.MovieDTO;
import lombok.Data;

import java.util.Set;

@Data
public class RateMoviesDTO {
    private Set<MovieDTO> movies;

    public RateMoviesDTO(Set<MovieDTO> movies) {
        this.movies = movies;
    }
}
