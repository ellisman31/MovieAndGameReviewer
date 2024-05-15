package com.movieandgamereview.informationservice.dto.actor;

import com.movieandgamereview.informationservice.dto.movie.MovieDTO;
import lombok.Data;

import java.util.Set;

@Data
public class ActorMoviesDTO {
    private Set<MovieDTO> movies;

    public ActorMoviesDTO(Set<MovieDTO> movies) {
        this.movies = movies;
    }
}
