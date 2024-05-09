package com.src.movieandgamereview.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ActorDTO {
    private PersonDTO person;
    private Set<MovieDTO> movies;
    private Set<GameDTO> games;

    public ActorDTO(PersonDTO person, Set<MovieDTO> movies, Set<GameDTO> games) {
        this.person = person;
        this.movies = movies;
        this.games = games;
    }
}
