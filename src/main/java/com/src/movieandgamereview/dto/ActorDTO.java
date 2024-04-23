package com.src.movieandgamereview.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ActorDTO {
    private PersonDTO person;
    private Set<MovieDTO> movies;
    private Set<GameDTO> games;

    public ActorDTO(PersonDTO person) {
        this.person = person;
        this.movies = new HashSet<>();
        this.games = new HashSet<>();
    }
}
